# Android Automation Testing with Java, Appium, & TestNG

This repository contains a Maven-based Android automation testing project using Java (JDK 21), Appium, Selenium, and TestNG. 

## Prerequisites & Environment Setup

To run these tests locally, you need to set up the following tools:

### 1. Java Development Kit (JDK 21)
- Install **JDK 21** (e.g., from [Eclipse Temurin](https://adoptium.net/) or Oracle).
- Configure your `JAVA_HOME` environment variable to point to the JDK 21 installation path.
- Add `%JAVA_HOME%\bin` (Windows) to your system `PATH`.

### 2. Maven
- Install **Apache Maven**.
- Add the Maven `bin` directory to your system `PATH`.
- Verify with `mvn -version`.

### 3. Node.js & Appium Server
Appium is built on Node.js.
1. Download and install **Node.js** (LTS version is recommended).
2. Open terminal/PowerShell and install **Appium Server** globally:
   ```bash
   npm install -g appium
   ```
3. Install the **Appium UiAutomator2 Driver** for Android:
   ```bash
   appium driver install uiautomator2
   ```

### 4. Android SDK & Emulator
1. Install **Android Studio** which includes the Android SDK Manager.
2. Configure the following environment variables:
   - `ANDROID_HOME` = `C:\Users\<YourUsername>\AppData\Local\Android\Sdk` (or your custom Android SDK location).
3. Add the following directories to your system `PATH`:
   - `%ANDROID_HOME%\platform-tools` (contains `adb`)
   - `%ANDROID_HOME%\emulator` (contains the Android emulator tool)
   - `%ANDROID_HOME%\tools`
   - `%ANDROID_HOME%\tools\bin`

---

## How to Run the Tests

### Step 1: Start an Android Emulator
Launch an Android Emulator using Android Studio AVD Manager, or from your terminal:
```bash
emulator -avd <Your_AVD_Name>
```
Verify the emulator is connected by running:
```bash
adb devices
```

### Step 2: Start the Appium Server
Start the Appium server by running the following command in a separate terminal:
```bash
appium
```
It should start listening on `http://127.0.0.1:4723`.

### Step 3: Run the Automation Tests
Run the Maven test command from the project root:
```bash
mvn clean test
```
This command compiles the project and triggers the TestNG suite defined in [testng.xml](src/test/resources/testng.xml).

---

## Project Structure

```text
├── pom.xml                        # Maven configuration (JDK 21, Appium, TestNG)
├── README.md                      # Instructions and documentation
└── src
    └── test
        ├── java
        │   └── com
        │       └── example
        │           ├── BaseTest.java     # Base setup (AndroidDriver, Capabilities)
        │           └── AndroidTest.java  # Sample TestNG tests
        └── resources
            └── testng.xml         # TestNG execution suite configuration
```
