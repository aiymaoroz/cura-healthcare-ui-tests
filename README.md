# 💻 CURA Healthcare UI Test Automation

Automated UI test suite for the [CURA Healthcare Service](https://katalon-demo-cura.herokuapp.com/) web application using **Java**, **Selenium WebDriver**, **TestNG**, and **Cucumber BDD**.  
This project covers end-to-end user flows including login, booking appointments, and logout.  
It is designed to demonstrate **professional-level framework design** and **CI integration**.

---

## 🧰 Tech Stack
| Tool / Library     | Purpose                         |
|--------------------|---------------------------------|
| Java               | Programming Language            |
| Selenium WebDriver | Browser automation              |
| Cucumber           | BDD & Gherkin syntax            |
| TestNG             | Test runner & assertions        |
| Maven              | Build and dependency management |
| GitHub Actions     | CI pipeline                     |
| WebDriverManager   | Automatic driver setup          |
| Lombok             | Simplified POJOs                |
| Page Object Model  | Clean code structure            |

---

## 📂 Project Structure
~~~
cura-healthcare-ui-tests/
├── src
│   ├── main
│   │   └── java
│   │       ├── pages/                  # Page Object classes
│   │       ├── models/                 # AppointmentDetails POJO
│   │       └── utilities/
│   │           ├── browser/            # Browser interface and implementations
│   │           ├── ConfigReader.java   # Reads from config.properties
│   │           ├── Driver.java         # Singleton WebDriver manager
│   │           └── Helper.java         # Common helper methods
│   └── test
│       ├── java
│       │   ├── stepdefinitions/        
│       │   │   ├── hooks/              # Before/After hooks
│       │   │   └── transformer/        # DataTable transformers
│       │   └── runners/                # TestNG runner
│       └── resources
│           ├── features/               # Gherkin test scenarios
│           └── properties/             # config.properties file
└── pom.xml                             # Maven config
~~~
---

## 🧪 Test Coverage
| Test Case Description                                            | Tags         | Result                   |
|------------------------------------------------------------------|--------------|--------------------------|
| Login with valid credentials                                     | `@Login`     | ✅ Pass                  |
| Login with invalid credentials (multiple inputs)                 | `@Login`     | ✅ Pass                  |
| Logout after successful login                                    | `@Login`     | ✅ Pass                  |
| Attempt to access appointment page after logout                  | `@Login`     | ✅ Pass                  |
| Navigate to appointment page                                     | `@Booking`   | ✅ Pass                  |
| Book appointment with all valid details (3 variations)           | `@Booking`   | ✅ Pass                  |
| Booking with empty visit date                                    | `@Booking`   | ✅ Pass                  |
| Booking fails with past date (*known issue*)                     | `@KnownBug`  | ⚠️ Fails *(known issue)* |

---

## ⚠️ Known Issue – Booking with Past Date
A known issue was identified where the app allows booking appointments **in the past**, which is incorrect behavior.  
This scenario is tagged with `@KnownBug` and excluded from regular test runs via tag filtering.
- ✅ Test case is kept for transparency and accountability  
- ✅ Tag-based filtering ensures the bug doesn’t block CI pipeline success  

---

## 💡 Framework Highlights
**📌 Singleton Driver Management**  
A thread-safe `Driver` class ensures only one WebDriver instance is created and reused across tests.

**📦 Page Object Model**  
Every page interaction is encapsulated in a dedicated class, promoting encapsulation and reusability.

**🧠 Helper Utilities**  
Common actions like safe clicking, smart waits, and JavaScript execution are centralized in `Helper.java`.

**🧼 Clean Data Handling**  
Gherkin `DataTables` are seamlessly mapped to `AppointmentDetails` POJO using Lombok and `@DataTableType transformer`.

**🏷️ Tag-Based Execution & Filtering**  
Tags like `@Login`, `@Booking`, `@KnownBug` allow flexible test runs.

**OOP Principles in Action**  
- Abstraction via `Browser` interface  
- Polymorphism through multiple browser implementations  
- Encapsulation in page & utility classes  
- Singleton design for WebDriver lifecycle management

**💥 Screenshot on Failure**  
Automatically captured and embedded in the Cucumber HTML report to aid debugging.

**⚙️ CI Integration with GitHub Actions**  
Fully automated headless test runs triggered on every push to `main`.

---

## Running the Tests
**✅ Locally**   
_git clone https://github.com/your-username/cura-healthcare-ui-tests.git  
cd cura-healthcare-ui-tests  
mvn clean test_   

**✅ GitHub Actions**
- CI runs headless tests on Chrome  
- HTML, JSON, and JUnit reports are saved in `target/CucumberReports`  
- Screenshots are automatically attached to failed scenarios  

---

## Reporting
| Format | Path                                           |
|--------|------------------------------------------------|
| HTML   | `target/CucumberReports/cucumber-reports.html` |
| JSON   | `target/CucumberReports/report.json`           |
| JUnit  | `target/CucumberReports/junit-report.xml`      |

---

## Author
**Aiyma (Amy) Orozobaeva**  
QA Automation Engineer  
📧 aiymaoroz@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/aiymaoroz) | [GitHub](https://github.com/aiymaoroz)
