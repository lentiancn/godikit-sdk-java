# godikit-sdk

[![License](https://img.shields.io/github/license/lentiancn/godikit-sdk-java.svg)](https://github.com/lentiancn/godikit-sdk-java/blob/main/LICENSE)
[![Codecov](https://img.shields.io/codecov/c/github/lentiancn/godikit-sdk-java.svg)](https://codecov.io/gh/lentiancn/godikit-sdk-java)
[![GitHub Release](https://img.shields.io/github/tag/lentiancn/godikit-sdk-java.svg?label=release)](https://github.com/lentiancn/godikit-sdk-java/releases)

English | [简体中文](README_zh-Hans.md) | [繁體中文](README_zh-Hant.md)

## Why godikit-sdk?

| Scenario                          | Without godikit-sdk                       | With godikit-sdk                                      |
|-----------------------------------|-------------------------------------------|-------------------------------------------------------|
| Null and blank checks             | Repetitive `isEmpty` / `isBlank` helpers  | Ready-to-use `StringUtils` and `ObjectUtils`          |
| Default value fallback            | Inline ternary null guards everywhere     | `defaultIfNull` / `defaultIfEmpty` / `defaultIfBlank` |
| Shared capability across services | Copy-pasted helpers in every project      | One dependency for the whole organization             |
| Third-party service clients       | Hand-written HTTP wrappers per project    | Dedicated SDK modules (planned)                       |
| JDK compatibility                 | One artifact cannot serve both toolchains | Separate JDK 8 and JDK 17 artifacts                   |

## Features

- **Null-Safe Helpers**: `ObjectUtils` covers null checks and generic default value resolution
- **String Guards**: `StringUtils` covers empty and blank checks with matching `defaultIf*` fallbacks
- **Zero Dependencies**: No third-party runtime dependencies, so the SDK adds nothing to your classpath
- **JDK 8 Baseline**: Compiled against JDK 8, with a dedicated JDK 17 artifact for modern toolchains
- **Extensible by Design**: Structured to host further SDK categories such as shared constants, identifier generation
  and third-party service client wrappers
- **Verified Bytecode Target**: Each artifact ships a compilation test that asserts the expected class file
  major version

## Architecture

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

### Planned SDK Categories

| Category         | Purpose                                      | Status    |
|------------------|----------------------------------------------|-----------|
| Utilities        | Null-safe string and object helpers          | Available |
| Shared Constants | Cross-module constants such as boolean flags | Planned   |
| Identifiers      | ID generation for entities and records       | Planned   |
| Service Clients  | Wrappers for third-party APIs                | Planned   |

## Requirements

- Java 8+
- Maven 3.6+

## Usage

### Maven

```xml
<!-- JDK 8 compatible -->
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

### Object Utilities

```java
import com.godikit.util.ObjectUtils;

// Resolve a fallback when the value is null
String name = ObjectUtils.defaultIfNull(user.getNickname(), "anonymous");

// Null checks
if(ObjectUtils.isNull(value)){
  // ...
}

if(ObjectUtils.isNotNull(value)){
  // ...
}
```

### String Utilities

```java
import com.godikit.util.StringUtils;

// Fallback for null, empty and blank values respectively
String city = StringUtils.defaultIfNull(raw, "Unknown");
String city = StringUtils.defaultIfEmpty(raw, "Unknown");
String city = StringUtils.defaultIfBlank(raw, "Unknown");

// Empty and blank checks
boolean empty = StringUtils.isEmpty(raw); // null or zero length
boolean blank = StringUtils.isBlank(raw);  // null or whitespace only
boolean notEmpty = StringUtils.isNotEmpty(raw);
boolean notBlank = StringUtils.isNotBlank(raw);
```

## API Reference

### `ObjectUtils`

| Method                        | Description                                       |
|-------------------------------|---------------------------------------------------|
| `defaultIfNull(O obj, O def)` | Returns `def` when `obj` is null, otherwise `obj` |
| `isNull(Object obj)`          | Returns `true` when `obj` is null                 |
| `isNotNull(Object obj)`       | Returns `true` when `obj` is not null             |

### `StringUtils`

| Method                                 | Description                                        |
|----------------------------------------|----------------------------------------------------|
| `defaultIfNull(String s, String def)`  | Returns `def` when `s` is null                     |
| `defaultIfEmpty(String s, String def)` | Returns `def` when `s` is null or empty            |
| `defaultIfBlank(String s, String def)` | Returns `def` when `s` is null or whitespace only  |
| `isEmpty(String s)`                    | Returns `true` when `s` is null or zero length     |
| `isNotEmpty(String s)`                 | Returns `true` when `s` is neither null nor empty  |
| `isBlank(String s)`                    | Returns `true` when `s` is null or whitespace only |
| `isNotBlank(String s)`                 | Returns `true` when `s` is neither null nor blank  |

## License

`godikit-sdk` is licensed under the [MIT License](LICENSE).

## Links

- Docs: https://www.godikit.com/sdk
- Issues: https://github.com/lentiancn/godikit-sdk-java/issues
