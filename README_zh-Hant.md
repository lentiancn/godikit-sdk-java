# godikit-sdk

[![License](https://img.shields.io/github/license/lentiancn/godikit-sdk-java.svg)](https://github.com/lentiancn/godikit-sdk-java/blob/main/LICENSE)
[![Codecov](https://img.shields.io/codecov/c/github/lentiancn/godikit-sdk-java.svg)](https://codecov.io/gh/lentiancn/godikit-sdk-java)
[![GitHub Release](https://img.shields.io/github/tag/lentiancn/godikit-sdk-java.svg?label=release)](https://github.com/lentiancn/godikit-sdk-java/releases)

繁體中文 | [English](README.md) | [简体中文](README_zh-Hans.md)

## 為什麼選擇 godikit-sdk？

| 場景                 | 不使用 godikit-sdk          | 使用 godikit-sdk                             |
|--------------------|---------------------------|----------------------------------------------|
| 空值與空白判斷            | 反覆編寫 `isEmpty` / `isBlank` 輔助方法 | 開箱即用的 `StringUtils` 與 `ObjectUtils`       |
| 預設值兜底                | 處處都是三元運算式空值判斷                | `defaultIfNull` / `defaultIfEmpty` / `defaultIfBlank` |
| 跨服務共享能力            | 每個專案複製貼上輔助方法                | 組織內統一依賴                                |
| 第三方服務用戶端             | 每個專案手寫 HTTP 封裝                   | 獨立的 SDK 模組（規劃中）                          |
| JDK 版本相容              | 單一構件無法同時適配兩套工具鏈               | 拆分 JDK 8 與 JDK 17 構件                        |

## 功能特性

- **空值安全輔助方法**：`ObjectUtils` 提供空值判斷與泛型預設值解析
- **字串守衛方法**：`StringUtils` 提供空字串與空白判斷，並配對 `defaultIf*` 兜底方法
- **零執行階段依賴**：不引入任何第三方執行階段依賴，不會為 classpath 增加負擔
- **以 JDK 8 為基線**：針對 JDK 8 編譯，同時提供適配現代工具鏈的 JDK 17 構件
- **面向擴充設計**：可承載更多 SDK 類別，例如共享常數、標識產生與第三方服務用戶端封裝
- **位元碼目標可驗證**：每個構件都附帶編譯測試，斷言預期的 class 檔案主版本號

## 架構設計

```
┌──────────────────────────────────────────────────────────────┐
│                       godikit-sdk                            │
│  (Reactor: groups the JDK 8 line and the JDK 17 line)       │
└──────────────────────────────────────────────────────────────┘
          ▲                                │
          │                                │
┌────────────────────────┐   ┌────────────────────────────────┐
│     godikit-sdk        │   │     godikit-sdk-jdk17          │
│  (Core artifact)       │   │  (Compatibility artifact)      │
│  com.godikit.util      │   │  Verifies the Java 17 target   │
│  StringUtils           │   └────────────────────────────────┘
│  ObjectUtils           │
└────────────────────────┘
```

### 規劃中的 SDK 類別

| 類別         | 用途                          | 狀態     |
|------------|-----------------------------|--------|
| 工具類         | 空值安全的字串與物件輔助方法                | 已提供    |
| 共享常數        | 跨模組共享的布林標識等常數                  | 規劃中   |
| 標識產生        | 為實體與記錄產生唯一標識                | 規劃中   |
| 服務用戶端       | 第三方 API 的封裝                | 規劃中   |

## 環境要求

- Java 8+
- Maven 3.6+

## 使用範例

### Maven 依賴

```xml
<!-- 相容 JDK 8 -->
<dependency>
  <groupId>com.godikit.sdk</groupId>
  <artifactId>godikit-sdk</artifactId>
  <version>1.0.0</version>
</dependency>

<!-- JDK 17 -->
<dependency>
  <groupId>com.godikit.sdk</groupId>
  <artifactId>godikit-sdk-jdk17</artifactId>
  <version>1.0.0</version>
</dependency>
```

### 物件工具類

```java
import com.godikit.util.ObjectUtils;

// 值為 null 時傳回兜底值
String name = ObjectUtils.defaultIfNull(user.getNickname(), "anonymous");

// 空值判斷
if (ObjectUtils.isNull(value)) {
    // ...
}

if (ObjectUtils.isNotNull(value)) {
    // ...
}
```

### 字串工具類

```java
import com.godikit.util.StringUtils;

// 分別針對 null、空字串與空白傳回兜底值
String city = StringUtils.defaultIfNull(raw, "Unknown");
String city = StringUtils.defaultIfEmpty(raw, "Unknown");
String city = StringUtils.defaultIfBlank(raw, "Unknown");

// 空字串與空白判斷
boolean empty = StringUtils.isEmpty(raw); // null 或長度為 0
boolean blank = StringUtils.isBlank(raw);  // null 或僅含空白字元
boolean notEmpty = StringUtils.isNotEmpty(raw);
boolean notBlank = StringUtils.isNotBlank(raw);
```

## API 參考

### `ObjectUtils`

| 方法                            | 說明                          |
|-------------------------------|-----------------------------|
| `defaultIfNull(O obj, O def)` | `obj` 為 null 時傳回 `def`，否則傳回 `obj` |
| `isNull(Object obj)`          | `obj` 為 null 時傳回 `true`                |
| `isNotNull(Object obj)`       | `obj` 不為 null 時傳回 `true`               |

### `StringUtils`

| 方法                                | 說明                                  |
|-----------------------------------|-------------------------------------|
| `defaultIfNull(String s, String def)` | `s` 為 null 時傳回 `def`             |
| `defaultIfEmpty(String s, String def)` | `s` 為 null 或空字串時傳回 `def`      |
| `defaultIfBlank(String s, String def)` | `s` 為 null 或僅含空白字元時傳回 `def` |
| `isEmpty(String s)`               | `s` 為 null 或長度為 0 時傳回 `true`         |
| `isNotEmpty(String s)`            | `s` 既不為 null 也不為空字串時傳回 `true`         |
| `isBlank(String s)`               | `s` 為 null 或僅含空白字元時傳回 `true`         |
| `isNotBlank(String s)`            | `s` 既不為 null 也不含空白字元時傳回 `true`         |

## 授權條款

`godikit-sdk` 基於 [MIT 授權條款](LICENSE) 發布。

## 相關連結

- 文件: https://www.godikit.com/sdk
- 問題回饋: https://github.com/lentiancn/godikit-sdk-java/issues
