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
- Factory design pattern

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
