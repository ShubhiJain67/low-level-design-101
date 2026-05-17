# Custom Logger Framework

A customizable structured logging framework built using Java.

This project demonstrates:
- Builder Design Pattern
- Singleton Design Pattern
- Strategy Pattern
- Thread Safety
- Structured Logging
- Fluent API Design
- Backend System Design Principles

The logger supports:
- contextual logging
- error metadata
- structured log entries
- configurable appenders
- thread-safe logging

---

# Problem Statement

Design a customizable logging framework that:
- supports multiple log levels
- builds logs step-by-step
- allows optional metadata
- supports contextual logging
- handles concurrent logging safely
- supports multiple output destinations

The system should be:
- extensible
- scalable
- thread-safe
- readable
- production-friendly

---

# Features

- Fluent Builder API
- Structured logging
- Contextual metadata support
- Error and exception logging
- Trace ID and Request ID support
- Multiple log levels
- Thread-safe logging
- Configurable appenders
- JSON/Text formatting support
- Singleton logger manager
- Extensible formatter strategy

---

# Supported Log Fields

| Field | Description |
|---|---|
| message | Main log message |
| level | INFO/WARN/ERROR/etc |
| timestamp | Log creation time |
| context | Service/module context |
| error | Exception details |
| errorType | Type/category of error |
| traceId | Distributed tracing id |
| requestId | Request correlation id |
| threadName | Current thread |
| metadata | Additional key-value data |

---

# Example Log Flow

```text
Application
    ↓
LogBuilder
    ↓
LogEntry
    ↓
Logger
    ↓
Appender
    ↓
Console/File/Cloud
```

---

# High-Level Architecture

```text
Application Threads
        ↓
LoggerManager (Singleton)
        ↓
Logger
        ↓
LogBuilder
        ↓
LogEntry
        ↓
Formatter Strategy
        ↓
Appender
```

---

# Design Patterns Used

| Pattern | Usage |
|---|---|
| Builder Pattern | Step-by-step log construction |
| Singleton Pattern | Centralized logger manager |
| Strategy Pattern | Log formatting |
| Factory Pattern | Appender creation |
| Observer Pattern (Future) | Async log subscribers |

---

# Why Builder Pattern?

Logs often contain:
- many optional fields
- dynamic metadata
- contextual information

Builder Pattern:
- improves readability
- avoids constructor explosion
- supports fluent APIs
- simplifies structured logging

---

# Why Singleton Pattern?

Logging should use:
- one centralized logging manager
- shared logging configuration
- consistent appenders and formatters

Singleton ensures:
- one logger manager instance exists globally

---

# Why Strategy Pattern?

Different log formatting styles may exist:
- JSON formatter
- text formatter
- compact formatter

Strategy Pattern allows:
- interchangeable formatting logic
- extensible formatting system

---

# Supported Log Levels

- DEBUG
- INFO
- WARN
- ERROR
- FATAL

---

# Folder Structure

```text
custom-logger/
│
├── logger/
│   ├── Logger.java
│   ├── LoggerManager.java
│
├── builder/
│   ├── LogBuilder.java
│
├── model/
│   ├── LogEntry.java
│   ├── LogLevel.java
│
├── formatter/
│   ├── LogFormatter.java
│   ├── JsonFormatter.java
│   ├── TextFormatter.java
│
├── appender/
│   ├── LogAppender.java
│   ├── ConsoleAppender.java
│   ├── FileAppender.java
│
├── exception/
│   ├── LoggerException.java
│
└── Main.java
```

---

# Core Components

| Component | Responsibility |
|---|---|
| LoggerManager | Singleton logging manager |
| Logger | Logging API |
| LogBuilder | Fluent log construction |
| LogEntry | Immutable log object |
| Formatter | Format log output |
| Appender | Output destination |

---

# Concurrency Concepts Used

| Concept | Purpose |
|---|---|
| Singleton | Shared logger manager |
| Concurrent Logging | Safe multi-thread access |
| Synchronization | Safe appender writes |
| Async Processing (Future) | Non-blocking logging |
| BlockingQueue (Future) | Async log buffering |

---

# Why Structured Logging?

Structured logs:
- improve debugging
- support observability systems
- integrate with monitoring tools
- enable log querying and analytics

Useful for:
- distributed systems
- microservices
- cloud-native systems

---

# Future Enhancements

## 1. Async Logging
Use:
- worker threads
- BlockingQueue
- non-blocking writes

---

## 2. File Rotation
Rotate logs:
- by size
- by time

---

## 3. Distributed Tracing Support
Support:
- trace IDs
- span IDs
- correlation IDs

---

## 4. Log Filtering
Filter logs:
- by level
- by service
- by environment

---

## 5. Cloud Appenders
Integrate with:
- AWS CloudWatch
- Elasticsearch
- Splunk

---

## 6. Log Compression
Compress archived logs.

---

## 7. Metrics & Monitoring
Track:
- logs/sec
- dropped logs
- error frequency

---

# Advantages

- Clean fluent API
- Extensible architecture
- Structured logging support
- Thread-safe design
- Readable log creation
- Flexible formatting
- Multiple appenders support

---

# Challenges Solved

- Constructor explosion
- Complex log metadata handling
- Concurrent logging
- Centralized logging configuration
- Extensible formatting system

---

# Interview Concepts Demonstrated

- Builder Pattern
- Singleton Pattern
- Strategy Pattern
- Fluent API Design
- Immutable Objects
- Thread Safety
- Structured Logging
- Backend System Design
- Concurrency Concepts

---

# Key Interview Talking Points

## Why Builder Pattern?
To simplify complex log creation with many optional fields.

---

## Why Singleton?
To ensure one centralized logger manager across the application.

---

## Why Strategy Pattern?
To support interchangeable log formatting implementations.

---

## Why Structured Logging?
To improve debugging, observability, and monitoring integration.

---

## Why Thread Safety?
Multiple application threads may log simultaneously.

---

# Strong Interview Statement

> “Designed a structured thread-safe custom logging framework using Builder, Singleton, and Strategy patterns with contextual logging and extensible formatting support.”

---

# Learning Outcomes

This project helps in understanding:
- production-grade Builder Pattern usage
- fluent API design
- centralized logging architecture
- concurrent backend systems
- extensible system design
- structured logging concepts

---

# Tech Stack

- Java
- OOP
- Design Patterns
- Concurrent Collections
- Multithreading
- Structured Logging
- Fluent APIs

---

# Summary

This project demonstrates how modern backend logging systems can be designed using:
- clean architecture
- scalable design patterns
- thread-safe logging mechanisms
- structured logging principles

It closely resembles concepts used in enterprise logging frameworks and backend production systems.