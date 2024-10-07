# Selenium - TDD Framework Design

## Tools / Technology stack:
- Selenium
- Java
- TestNG
- JUnit
- Maven
- Rest Assured
- Jackson Data bind API
- Allure
- GitHub
- Jenkins


## Using Industry Best Practices, Design Patterns & API Integration

### Design Patterns
- Page Object Model design
    - *Functional vs Structural Page Object*
    - *Handle Dynamic Ui Element*
    - *Common Method to Load URL*
- Fluent Interface
- Builder pattern
- Singleton design pattern
- Page Factory design pattern
  - Page Factory or no Page Factory (`By` class) ?

### OOP concepts
- Inheritance
- Encapsulation
- Composition
- Abstraction
- Polymorphism

### API Integration
- Setup test data
- Setup application state
- Skip login through UI
- Inject cookies

### Selenium Guidelines
- Separation between tests & locators
- Page component objects
- Generating app state
- Avoid sharing test data
- New driver instance per test
- independent tests

### Good programming practices
- Single Responsibility Principle (SRP)
- Do not Repeat Yourself (DRY) principle
- Composition vs Inheritance
- Java naming conventions
  - *Give Good Method Name*
    - So that test case is more readable `"HomePage.clickStoreMenuLink()"` to `"HomePage.goToStoreUsingMenu()"`
    - So that the test case is representing more of what is happening  rather than how it’s happening for example `"HomePage.goToStoreUsingMenu()"` or `"HomePage.navigateToStoreUsingMenu()"` represents the `“what”` while `"HomePage.clickStoreMenuLink()"` represents the `how()` -—> we should avoid the `“how”` part in the test case 


### Other guidelines / practices
- **Manage test data (ie Data Objects) as Java Objects using POJOs** (eg BillingAddress, Products, Users )
  - Used Builder design pattern - allow chaining methods 
  - Create a JSON file - to store the test data and re-use data (eg BillingAddress, Products)
    - using *Jackson Databind* to [de-serialize the JSON file](https://stackoverflow.com/questions/3316762/what-is-deserialize-and-serialize-in-json)
    - used a *Parameterized constructor* - more *flexibility* of using the object & increase *readability*
  - Pros: *Low maintenance* + *DRY* + *SRP*
    - if there's a field added or removed on the form - it's easier to manage via POM without updating the test case OR changing it in multiple locations
       - *Good Practice:* ideally a test case should be updated only if there's a change in the requirement

- **Synchronization**
  - use *implicit or explicit (preferred) or fluent waits* for the Selenium WebDriver to wait desired state of the DOM (on the browser)
  - Explicit Wait Strategies
    - wait for page title/URL to show 
    - wait for slowest element to show  (but not reliable AJAX may reload some other element)
    - wait for each UI element  (slowest but most reliable on AJAX calls reloading the DOM and flexible which expected conditions to be met)

- **Test Optimizations**
  - remove/avoid  user and application state dependency
    - eg `billing country/city` address are set by the app to default OR could be set by another user/test to `USA/California` 
    - eg `Payment option` is set by default on the app as `Direct Bank transfer` BUT it could be set by app/another user/ another test to `Cash on delivery`
    - *Good Practice :* we need to remove/avoid user and application state dependency by selecting the `billing country/city` and `Payment option` on the test

- **Page Factory**
  - implemented on the `CartPage` class
  - Page Factory or no Page Factory (ie use `By` class) - is up to individual preferences 

- **Automated Driver Management**
  - added on the `DriverManager` class
  
- **Multiple browser support**
  - chrome, firefox

- **Different ways to Drive Automation**
  - `ThumbRule:` integrate your 1st test case to the CI process as soon as possible
    - get immediate feedback on the performance of the automation
  1. Maven - by setting system properties  (without TestNG)
     - mvn command without passing browser name (with default browser set on reading system properties eg `CHROME`)
       - `mvn clean test`
     - mvn command to pass browser name 
       - `mvn clean test -Dbrowser=CHROME`
       - `mvn clean test -Dbrowser=FIREFOX`
  2. IDE (IntelliJ) - by setting JVM arguments 
     - TestNG template configurations : `-ea -Dbrowser=FIREFOX`
  3. TestNG.xml  
     - using system properties (JVM arguments)
     - using parameters (Note: unset the JVM arguments as it overrides TestNG parameters)
  4. TestNG.xml using Maven command 
     - using surefire plugin (with `testng.xml` set on `pom.xml`)
       - Default (Chrome): `mvn clean test`
     - using system properties
       - Chrome: `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml`
       - Firefox: `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml,testng_firefox.xml`
     - use default TestNG.xml and make automation flexible 
       - `pom.xml`: 
       - ```xml 
            <properties><suiteFile>testng.xml</suiteFile></properties>
         ```
       - Firefox: `mvn clean test -DsuiteFile=testng.xml`
       - Default (Chrome): `mvn clean test`

- **Parallel Execution**
  - very important to design the automation for parallel execution AND it should not be an afterthought
  - Basic Principles: **Independent tests** by:
    1. no TestNG priorities
    2. avoid instance variables (TODO: re-visit)
    3. use stateless methods (ie use local variables in methods)
    4. no driver sharing
    5. no sharing of test data or application/user state
    6. avoid static variables (use thread local variables)
  - Option 1:  Drive parallel execution using Maven surefire plugin - https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
  - Option 2: Drive parallel execution using TestNG - `testng_parallel_execution.xml` or cloud options (browserstack, saucelabs etc) - cause of slow execution
  - Option 2: Drive parallel execution using TestNG via Maven (see above surefire settings)

- **JUnit vs TestNG**
  - No need to use `ThreadLocal` class - each test will get an different instance of the `BaseTest` class and the test classes 
  - So we don't need to use the `ThreadLocal` class to create separate copies of the `WebDriver` as there's no shared instance (like on `TestNG`)

-  **Singleton Design Pattern**
  - remove hardcoded global (initialized/loaded once, shared across the framework) configs  using `config.properties` file
    - ie hardcoded `baseUrl, username and password` 
  - use the singleton config loader class `utils.ConfigLoader` where the constructor is private




## Bad Practices
- Non-atomic tests
- Code duplication
- Duplicate element definition
- User state dependency
  - Handling user state dependency by clearing text fields before filling them - for example registered users have billing address details pre-loaded on checkout page
- Application state dependency
- Non-readable tests
- Static sleeps
- Hardcoding
- Lacking multiple browser support
- Lacking multiple env support


## Page Object Model (POM) design

### What is POM
- Each page is represented by a class
- Contains the UI element definitions and the methods for user actions
- Can use/implement the Fluent interface and Builder design patterns

### Advantages of POM
- Encapsulation (of both elements/variables and methods esp structural methods)
- Encourages some level of (Single Responsibility Principle) SRP
- Increase code re-usability
- Increase code readability
- Low maintenance

### Thumb rules
- use good names (the `what` over `how` on test cases)
- use private variables 
- no assertions on page classes (except asserting the page title - when required)
- include no other task other than the required user actions
- avoid bulky objects (composition vs inheritance)
