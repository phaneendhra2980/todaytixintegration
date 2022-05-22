# Introduction
Automatin Framework for functional UI and end to end tests for https://www.encoretickets.co.uk/ website and Mobile App. This project uses the page object model (POM) design.

This framework uses PageFactory method to store the WebElements in the page objects.

## Stack & Libraries
- Java
- Selenium WebDriver
- TestNG 
- Maven
- Extent Reports and logging

## Project components
1. **config** -  Contains Configuration Properties like Application URL, Browser

2. **listener** - Contains all Start and End Activities onFinish, onTestStart,...
3. **pages** - Page objects are in the directory *src/main/java/pages*.
4. **reports** - Extent Report are in the directory *src/main/java/reports*
5. **services** - API Service objects are in the directory *src/main/java/services*
6. **utils** - Frameowrk Utilites are in the directory *src/main/java/utils*
7. **tests** - Test Scripts are in the directory *src/test/java/tests*
8. **pom.xml** This framework has been designed with following maven dependencies as defined further in the *pom.xml*.
	| Dependency | Version | Purpose |
	|--|--|--|
	| Selenium WebDriver|>v4| It automates the testing of web application.|
	| TestNG|>v7|To run the tests as a suite.|
	| Extent Reports|>v5|To genarate Enhanced html reports after test run.|	
	| webdrivermanager|>5| WebDriverManager is a library which allows to automate the management of the drivers (e.g. _chromedriver_, _geckodriver_, etc.) required by Selenium WebDriver. [bonigarcia/webdrivermanager: Automated driver management for Selenium WebDriver (github.com)](https://github.com/bonigarcia/webdrivermanager#basic-usage)|
	
   
  
  ## Highlights
- This framework supports Chrome browser
- This uses **WebDriverManager**, an open source browser binary manager. Selenium WebDriver requires you to download the binaries for each driver and hardcode the path of the driver. The disadvantage of this is each user must change the code to match their path, and they must manually check when new versions of the binaries are released. WebDriverManager automatically checks the latest version of the browser downloaded on your machine and downloads the required driver binary into the cache, eliminating the need to manually download and store binary files.
- Screenshot on test failure: A screenshot of the active browser is captured and stored in the screenshots folder
- Extent reporting and logging: After the test finishes, a visual report is generated for all the executed test cases from the suite. This report can be found in the 'TestReport' folder
