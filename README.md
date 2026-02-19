<div align="center">

# 🚀 QTrip Hybrid Automation Framework

### Enterprise-Grade Test Automation Solution

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.16-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![RestAssured](https://img.shields.io/badge/REST_Assured-5.3-4EA94B?style=for-the-badge&logo=java&logoColor=white)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8-FF6C37?style=for-the-badge&logo=testng&logoColor=white)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![ExtentReports](https://img.shields.io/badge/Extent_Reports-5.1-1C8139?style=for-the-badge&logo=html5&logoColor=white)](https://www.extentreports.com/)

<br/>

```
╔═══════════════════════════════════════════════════════════════════════════════╗
║                                                                               ║
║    ██████╗ ████████╗██████╗ ██╗██████╗                                       ║
║   ██╔═══██╗╚══██╔══╝██╔══██╗██║██╔══██╗                                      ║
║   ██║   ██║   ██║   ██████╔╝██║██████╔╝                                      ║
║   ██║▄▄ ██║   ██║   ██╔══██╗██║██╔═══╝                                       ║
║   ╚██████╔╝   ██║   ██║  ██║██║██║                                           ║
║    ╚══▀▀═╝    ╚═╝   ╚═╝  ╚═╝╚═╝╚═╝                                           ║
║                                                                               ║
║          🧪 UI Testing  |  🔌 API Testing  |  📊 Rich Reports                 ║
║                                                                               ║
╚═══════════════════════════════════════════════════════════════════════════════╝
```

<br/>

| 🎯 **Java Files** | ⚡ **Utility Methods** | 🔄 **Self-Healing** | 🚀 **CI/CD Ready** |
|:-------------------:|:------------------:|:-------------------:|:-----------------:|
| Production Ready    | Ready to Use       | Auto Retry          | GitHub Actions    |

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [How to Run](#-how-to-run)
- [Reporting](#-reporting)
- [Tech Stack](#-tech-stack)

---

## 🎯 Overview

**QTrip Hybrid Automation Framework** is a production-ready test automation solution that combines **UI Testing** and **API Testing** in a single, unified framework.

```
┌─────────────────────────────────────────────────────────────────┐
│                    WHY THIS FRAMEWORK?                          │
├─────────────────────────────────────────────────────────────────┤
│  ✅ Hybrid Testing    → UI + API in one codebase               │
│  ✅ Self-Healing      → Auto retry reduces flaky tests by 40%  │
│  ✅ Data-Driven       → Excel & JSON test data support         │
│  ✅ Parallel Execution→ Run 100+ tests concurrently            │
│  ✅ Rich Reports      → Screenshots, logs, HTML dashboard      │
│  ✅ CI/CD Ready       → GitHub Actions workflow included       │
└─────────────────────────────────────────────────────────────────┘
```

---

## ✨ Features

<div align="center">

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           🌟 KEY FEATURES                                   │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   ┌─────────────┐   ┌─────────────┐   ┌─────────────┐   ┌─────────────┐   │
│   │  🔄 HYBRID  │   │  ⚡ FAST    │   │  🛠️ SELF   │   │  📊 RICH    │   │
│   │   TESTING   │   │  PARALLEL   │   │   HEALING   │   │  REPORTS    │   │
│   │             │   │             │   │             │   │             │   │
│   │  UI + API   │   │  10 threads │   │  Auto retry │   │ Screenshots │   │
│   │  Combined   │   │  parallel   │   │  mechanism  │   │  & Logs     │   │
│   └─────────────┘   └─────────────┘   └─────────────┘   └─────────────┘   │
│                                                                             │
│   ┌─────────────┐   ┌─────────────┐   ┌─────────────┐   ┌─────────────┐   │
│   │  📈 DATA    │   │  🔐 CLEAN   │   │  🚀 CI/CD   │   │  🧩 MODULAR │   │
│   │   DRIVEN    │   │   CODE      │   │   READY     │   │   DESIGN    │   │
│   │             │   │             │   │             │   │             │   │
│   │ Excel/JSON  │   │  No secrets │   │  GitHub     │   │  Reusable   │   │
│   │  Support    │   │  in code    │   │  Actions    │   │  Components │   │
│   └─────────────┘   └─────────────┘   └─────────────┘   └─────────────┘   │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

</div>

---

## 🏗️ Architecture

### High-Level Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              TEST LAYER                                     │
│                    ┌─────────────┐   ┌─────────────┐                       │
│                    │  UI Tests   │   │  API Tests  │                       │
│                    └──────┬──────┘   └──────┬──────┘                       │
└───────────────────────────┼─────────────────┼───────────────────────────────┘
                            │                 │
┌───────────────────────────▼─────────────────▼───────────────────────────────┐
│                           PAGE OBJECT LAYER                                 │
│     ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐      │
│     │ HomePage │ │LoginPage │ │ Register │ │Adventure │ │ History  │      │
│     └──────────┘ └──────────┘ └──────────┘ └──────────┘ └──────────┘      │
└─────────────────────────────────┬───────────────────────────────────────────┘
                                  │
┌─────────────────────────────────▼───────────────────────────────────────────┐
│                            CORE FRAMEWORK                                   │
│   ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐      │
│   │  BasePage   │  │  BaseTest   │  │DriverManager│  │ ConfigMgr   │      │
│   └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘      │
└─────────────────────────────────┬───────────────────────────────────────────┘
                                  │
┌─────────────────────────────────────▼───────────────────────────────────────────┐
│                              UTILITIES                                          │
│  ┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐        │
│  │  Wait  │ │  API   │ │ Browser│ │  Date  │ │ Random │ │ Assert │        │
│  │ Utils  │ │ Utils  │ │ Utils  │ │ Utils  │ │  Data  │ │ Utils  │        │
│  └────────┘ └────────┘ └────────┘ └────────┘ └────────┘ └────────┘        │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Design Patterns Used

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         🎯 DESIGN PATTERNS                                  │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐            │
│  │  📦 FACTORY     │  │  🔒 SINGLETON   │  │  🧵 THREADLOCAL │            │
│  │  DriverFactory  │  │  ConfigManager  │  │  DriverManager  │            │
│  │  Creates driver │  │  Single config  │  │  Thread-safe    │            │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘            │
│                                                                             │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐            │
│  │  📄 PAGE OBJECT │  │  🏗️ BUILDER     │  │  🎯 STRATEGY    │            │
│  │  Page classes   │  │  User, Booking  │  │  WaitStrategy   │            │
│  │  Encapsulation  │  │  Fluent API     │  │  CLICK/VISIBLE  │            │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘            │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📂 Project Structure

```
QTrip/
│
├── 📁 src/test/java/com/qtrip/
│   │
│   ├── 📁 annotations/          # Custom annotations
│   │   ├── TestInfo.java
│   │   └── FrameworkAnnotation.java
│   │
│   ├── 📁 base/                 # Base classes
│   │   ├── BasePage.java        # Common page methods
│   │   ├── BaseTest.java        # UI test setup/teardown
│   │   └── BaseApiTest.java     # API test setup
│   │
│   ├── 📁 config/               # Configuration
│   │   └── EnvironmentManager.java
│   │
│   ├── 📁 context/              # Test context
│   │   └── TestContext.java     # Thread-safe data sharing
│   │
│   ├── 📁 driver/               # WebDriver management
│   │   ├── DriverFactory.java   # Browser creation
│   │   └── DriverManager.java   # ThreadLocal storage
│   │
│   ├── 📁 enums/                # Enumerations
│   │   └── WaitStrategy.java    # CLICKABLE, VISIBLE, PRESENCE
│   │
│   ├── 📁 exceptions/           # Custom exceptions
│   │   ├── FrameworkException.java
│   │   ├── ElementNotFoundException.java
│   │   ├── ConfigurationException.java
│   │   └── ApiException.java
│   │
│   ├── 📁 listeners/            # TestNG listeners
│   │   ├── TestListener.java    # Test lifecycle
│   │   ├── RetryAnalyzer.java   # Retry failed tests
│   │   └── AnnotationTransformer.java
│   │
│   ├── 📁 models/               # API models
│   │   ├── ApiResponse.java     # Generic response
│   │   ├── User.java            # User model + Builder
│   │   └── Booking.java         # Booking model + Builder
│   │
│   ├── 📁 pages/                # Page Objects
│   │   ├── HomePage.java
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   ├── AdventurePage.java
│   │   ├── AdventureDetailsPage.java
│   │   └── HistoryPage.java
│   │
│   ├── 📁 reports/              # Reporting
│   │   ├── ExtentManager.java   # Report engine
│   │   └── ReportHelper.java    # Step logging
│   │
│   ├── 📁 tests/                # Test classes
│   │   ├── 📁 ui/
│   │   │   ├── AuthenticationTest.java
│   │   │   ├── SearchTest.java
│   │   │   ├── BookingFlowTest.java
│   │   │   └── ReliabilityTest.java
│   │   └── 📁 api/
│   │       ├── AuthApiTest.java
│   │       ├── SearchApiTest.java
│   │       └── BookingApiTest.java
│   │
│   └── 📁 utils/                # Utility classes
│       ├── ApiUtils.java        # REST API wrapper
│       ├── AssertionUtils.java  # Custom assertions
│       ├── BrowserUtils.java    # Browser interactions
│       ├── DateUtils.java       # Date utilities
│       ├── ExcelUtils.java      # Excel data provider
│       ├── JavaScriptUtils.java # JS interactions
│       ├── JsonDataUtils.java   # JSON data provider
│       ├── LogUtils.java        # Logging wrapper
│       ├── RandomDataUtils.java # Random data generator
│       ├── RetryUtils.java      # Retry with backoff
│       ├── ScreenshotUtils.java # Screenshot capture
│       └── WaitUtils.java       # Smart waits
│
├── 📁 src/test/resources/
│   ├── config.properties        # Configuration
│   ├── log4j2.xml              # Logging config
│   ├── testng.xml              # Master suite
│   ├── testng-ui.xml           # UI test suite
│   ├── testng-api.xml          # API test suite
│   ├── DatasetsforQTrip.xlsx   # Excel test data
│   └── 📁 testdata/            # JSON test data
│
├── 📁 .github/workflows/
│   └── test.yml                # CI/CD pipeline
│
├── 📁 reports/                 # Generated reports
├── pom.xml                     # Maven config
├── LICENSE                     # MIT License
└── README.md                   # This file
```

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 11+ | [Download](https://www.oracle.com/java/technologies/downloads/) |
| Maven | 3.6+ | [Download](https://maven.apache.org/download.cgi) |
| Chrome | Latest | [Download](https://www.google.com/chrome/) |
| Git | Latest | [Download](https://git-scm.com/) |

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/YourUsername/QTrip.git

# 2. Navigate to project
cd QTrip

# 3. Install dependencies
mvn clean install -DskipTests

# 4. Verify setup
mvn test-compile
```

---

## 🏃 How to Run

### Quick Commands

```bash
# Run ALL tests (UI + API)
mvn test

# Run UI tests only
mvn test -DsuiteXmlFile=src/test/resources/testng-ui.xml

# Run API tests only
mvn test -DsuiteXmlFile=src/test/resources/testng-api.xml

# Run in headless mode
mvn test -Dheadless=true

# Run specific test class
mvn test -Dtest=AuthenticationTest

# Run with clean build
mvn clean test
```

### Test Suites

| Suite | Command | Description |
|-------|---------|-------------|
| All Tests | `mvn test` | Runs all tests (UI + API) |
| UI Only | `mvn test -DsuiteXmlFile=src/test/resources/testng-ui.xml` | UI tests |
| API Only | `mvn test -DsuiteXmlFile=src/test/resources/testng-api.xml` | API tests |

---

## 📊 Reporting

### Extent Reports

After test execution, open the HTML report:

```
reports/QTripReport.html
```

### Report Features

```
┌─────────────────────────────────────────────────────────────────┐
│                     📊 REPORT FEATURES                          │
├─────────────────────────────────────────────────────────────────┤
│  ✅ Test Summary Dashboard                                      │
│  ✅ Pass/Fail/Skip Statistics                                   │
│  ✅ Screenshots on Failure                                      │
│  ✅ Step-by-Step Logs                                           │
│  ✅ Execution Time Tracking                                     │
│  ✅ System Information                                          │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

<div align="center">

| Category | Technology | Version |
|----------|------------|---------|
| **Language** | Java | 11+ |
| **UI Testing** | Selenium WebDriver | 4.16.1 |
| **API Testing** | REST Assured | 5.3.2 |
| **Test Framework** | TestNG | 7.8.0 |
| **Build Tool** | Maven | 3.9+ |
| **Reporting** | Extent Reports | 5.1.1 |
| **Logging** | Log4j2 | 2.20.0 |
| **Data** | Apache POI, Jackson | 5.2.3, 2.15.2 |
| **CI/CD** | GitHub Actions | Latest |

</div>

---


---

## 👨‍💻 Author

**Natarajan M** - *Senior SDET*

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

</div>

