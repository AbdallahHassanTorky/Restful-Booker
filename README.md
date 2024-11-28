---
# API Automation Testing - C2 General Assessment

This repository contains API automation tests for verifying the functionality of the **Restful Booker API**. The tests are written in **Java**, using **Rest Assured** for HTTP requests, **TestNG** for test execution, and reports are generated using **ExtentReports** or **Allure**.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Prerequisites](#prerequisites)
3. [Tools and Technologies](#tools-and-technologies)
4. [Setup and Installation](#setup-and-installation)
5. [Test Scenarios](#test-scenarios)
6. [Running Tests](#running-tests)
7. [Generating Reports](#generating-reports)
8. [Clean Code Guidelines](#clean-code-guidelines)


---

## Project Overview

This project contains automation test cases for three main scenarios:

1. **Scenario 1**: Verify that a token is successfully generated upon authentication with valid credentials.
2. **Scenario 2**: Verify that a booking can be created successfully with valid input data.
3. **Scenario 3**: Verify that the list of bookings can be retrieved and contains at least one entry.

All tests are automated using **Java**, **Rest Assured**, and **TestNG**.

---

## Prerequisites

Before you begin, ensure you have the following software installed on your machine:

1. **Java 11+** (You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/)).
2. **Maven** (for dependency management and building the project). You can install it from [Maven's website](https://maven.apache.org/download.cgi).
3. **IDE** (e.g., IntelliJ IDEA, Eclipse) to open and run the project.
4. **Git** (to clone the repository).

---

## Tools and Technologies

This project uses the following tools and technologies:

- **Java 23**
- **Rest Assured** (for API testing)
- **TestNG** (for test execution and reporting)
- **Maven** (for dependency management)
- **ExtentReports** / **Allure** (for generating test execution reports)
- **JSON** (for request/response data format)
- **JSON** (for data-driven tests)

---

## Setup and Installation

### Clone the repository

First, clone this repository to your local machine using Git:

```bash
git clone https://github.com/yourusername/api-automation-assessment.git
```

### Install dependencies

Once the repository is cloned, navigate to the project folder and run the following Maven command to install the dependencies:

```bash
mvn install
```

This will download all the required libraries (e.g., Rest Assured, TestNG, etc.) and set up the project environment.

---

## Test Scenarios

### Scenario 1: Verify Token Generation

- **Endpoint**: `POST https://restful-booker.herokuapp.com/auth`
- **Request Data**:
  ```json
  {
    "username": "admin",
    "password": "password123"
  }
  ```
- **Expected Response**:
  ```json
  {
    "token": "abc123"
  }
  ```
- **Test**: The test checks that the response is not empty and contains a valid `token`.

### Scenario 2: Verify Booking Creation

- **Endpoint**: `POST https://restful-booker.herokuapp.com/booking`
- **Request Data** (Generate your own data):
  ```json
  {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
      "checkin": "2018-01-01",
      "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
  }
  ```
- **Expected Response**:
  ```json
  {
    "bookingid": 1,
    "booking": {
      "firstname": "Jim",
      "lastname": "Brown",
      "totalprice": 111,
      "depositpaid": true,
      "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
      },
      "additionalneeds": "Breakfast"
    }
  }
  ```

### Scenario 3: Verify Booking List

- **Endpoint**: `GET https://restful-booker.herokuapp.com/booking`
- **Test**: Retrieve the list of bookings and verify that the list contains at least one booking.

---

## Running Tests

1. Open the project in your IDE.
2. Ensure Maven dependencies are installed by running:
   ```bash
   mvn clean install
   ```
3. Execute the tests using TestNG. In your IDE, you should be able to right-click the TestNG XML file (`testng.xml`) and run it.
4. Alternatively, you can run the tests from the command line using Maven:
   ```bash
   mvn test
   ```

This will trigger the TestNG suite to run all defined tests.

---

## Generating Reports

- **Allure**: If you choose to use Allure for reports, make sure to follow the instructions in the [Allure documentation](https://allure.qatools.ru/) for integration with Maven and TestNG.

---

## Clean Code Guidelines

The following clean code practices were followed in the development of this project:

1. **Naming conventions**: Meaningful variable, method, and class names.
2. **Modularization**: Methods and classes are small, single-purpose, and reusable.
3. **Comments**: Each test case and method has descriptive comments.
4. **No hardcoded values**: Parameters like URLs, credentials, and other configurations are parameterized.

---
