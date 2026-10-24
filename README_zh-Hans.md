# godikit-sdk

[![License](https://img.shields.io/github/license/lentiancn/godikit-sdk-java.svg)](https://github.com/lentiancn/godikit-sdk-java/blob/main/LICENSE)
[![Codecov](https://img.shields.io/codecov/c/github/lentiancn/godikit-sdk-java.svg)](https://codecov.io/gh/lentiancn/godikit-sdk-java)
[![GitHub Release](https://img.shields.io/github/tag/lentiancn/godikit-sdk-java.svg?label=release)](https://github.com/lentiancn/godikit-sdk-java/releases)

简体中文 | [繁體中文](README_zh-Hant.md) | [English](README.md)

## 为什么选择 godikit-sdk？

| 场景                 | 不使用 godikit-sdk          | 使用 godikit-sdk                             |
|--------------------|---------------------------|----------------------------------------------|
| 空值与空白判断 | 反复编写 `isEmpty` / `isBlank` 辅助方法 | 开箱即用的 `StringUtils` 与 `ObjectUtils`       |
| 默认值兜底                | 到处都是三元表达式空值判断                | `defaultIfNull` / `defaultIfEmpty` / `defaultIfBlank` |
| 跨服务共享能力            | 每个项目复制粘贴辅助方法                | 组织内统一依赖                                |
| 第三方服务客户端             | 每个项目手写 HTTP 封装                   | 独立的 SDK 模块（规划中）                          |
| JDK 版本兼容 | 单一构件无法同时适配两套工具链               | 拆分 JDK 8 与 JDK 17 构件                        |

## 功能特性

- **空值安全辅助方法**：`ObjectUtils` 提供空值判断与泛型默认值解析
- **字符串守卫方法**：`StringUtils` 提供空串与空白判断，并配对 `defaultIf*` 兜底方法
- **零运行时依赖**：不引入任何第三方运行时依赖，不会给 classpath 增加负担
- **以 JDK 8 为基线**：面向 JDK 8 编译，同时提供适配现代工具链的 JDK 17 构件
- **面向扩展设计**：可承载更多 SDK 类别，例如共享常量、标识生成与第三方服务客户端封装
- **字节码目标可验证**：每个构件都附带编译测试，断言预期的 class 文件主版本号

## 架构设计

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

### 规划中的 SDK 类别

| 类别         | 用途                          | 状态     |
|------------|-----------------------------|--------|
| 工具类         | 空值安全的字符串与对象辅助方法                | 已提供    |
| 共享常量        | 跨模块共享的布尔标识等常量                  | 规划中   |
| 标识生成        | 为实体与记录生成唯一标识                | 规划中   |
| 服务客户端       | 第三方 API 的封装                | 规划中   |

## 环境要求

- Java 8+
- Maven 3.6+

## 使用示例

### Maven 依赖

```xml
<!-- 兼容 JDK 8 -->
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

### 对象工具类

```java
import com.godikit.util.ObjectUtils;

// 值为 null 时返回兜底值
String name = ObjectUtils.defaultIfNull(user.getNickname(), "anonymous");

// 空值判断
if (ObjectUtils.isNull(value)) {
    // ...
}

if (ObjectUtils.isNotNull(value)) {
    // ...
}
```

### 字符串工具类

```java
import com.godikit.util.StringUtils;

// 分别针对 null、空串与空白返回兜底值
String city = StringUtils.defaultIfNull(raw, "Unknown");
String city = StringUtils.defaultIfEmpty(raw, "Unknown");
String city = StringUtils.defaultIfBlank(raw, "Unknown");

// 空串与空白判断
boolean empty = StringUtils.isEmpty(raw); // null 或长度为 0
boolean blank = StringUtils.isBlank(raw);  // null 或仅含空白字符
boolean notEmpty = StringUtils.isNotEmpty(raw);
boolean notBlank = StringUtils.isNotBlank(raw);
```

## API 参考

### `ObjectUtils`

| 方法 | 说明 |
|-------------------------------|-----------------------------|
| `defaultIfNull(O obj, O def)` | `obj` 为 null 时返回 `def`，否则返回 `obj` |
| `isNull(Object obj)`          | `obj` 为 null 时返回 `true`                |
| `isNotNull(Object obj)`       | `obj` 不为 null 时返回 `true`               |

### `StringUtils`

| 方法                                | 说明                                  |
|-----------------------------------|-------------------------------------|
| `defaultIfNull(String s, String def)` | `s` 为 null 时返回 `def`             |
| `defaultIfEmpty(String s, String def)` | `s` 为 null 或空串时返回 `def`      |
| `defaultIfBlank(String s, String def)` | `s` 为 null 或仅含空白字符时返回 `def` |
| `isEmpty(String s)`               | `s` 为 null 或长度为 0 时返回 `true` |
| `isNotEmpty(String s)`            | `s` 既不为 null 也不为空串时返回 `true`         |
| `isBlank(String s)`               | `s` 为 null 或仅含空白字符时返回 `true`         |
| `isNotBlank(String s)`            | `s` 既不为 null 也不含空白字符时返回 `true`         |

## 授权条款

`godikit-sdk` 基于 [MIT 授权条款](LICENSE) 发布。

## 相关链接

- 文档: https://www.godikit.com/sdk
- 问题反馈: https://github.com/lentiancn/godikit-sdk-java/issues
