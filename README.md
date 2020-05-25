# Console Scooter Rental App
A simple command line application that renting scooters in text form.
![](https://www.komputronik.pl/informacje/wp-content/uploads/2019/01/Hulajnoga-elektryczna-jak-wybra%C4%87.jpg)

#### Requirements
The app requires:

1. Java Development Kit

2. PostgreSQL PostgresSQL >=12.2

3. Maven

4. Lombok Plugin

Create PostgresSQL database: **scooter_rental_db**
with user postgres and password postgres
Alternatively, the setup can be changed in file application.properties in src/main/resources/
Fill in the information about the created Database in 
src\main\resources\application.properties

Run app,

#### How to use App

Enter ID number 0-10 in console

| ID  | Function |	Description |
| :------------ |:---------------:| -----:|
| 1 |`addingUser()` | Register user account with request parameters ( username, ownerAge, email)|
| 2 | `deleteUser()`| You can delete account via email  |
| 3 | `findAllUsers()`| You can find all users in database |
| 4 | `addingScooter()`| Register user account with request parameters (model,maxSpeed,rental price  ) |
| 5 | `deleteScooter()`|You can delete Scooter via ID  |
| 6| `findAllScooters()`| You can find all scooter in database |
| 7 | `rentScooter()`| Register rent with request parameters ( scooterId , accountId) |
|8 | `returnScooter()`|  Return Scooter by id |
|9| `addingScooterDock()`| Register user account with request parameters ( dockName, availablePlace ) |
|10 | `deleteScooterDock()`| You can delete Scooter Dock via ID|
|0| `exit`| |

### Technologies used :

* [Maven](https://maven.apache.org/) - Dependency Management
* [SLF4J](http://www.slf4j.org/) - Logging and printing to console
* [Project Lombok](https://projectlombok.org/) - Reduces boiler-plate code
* [JUnit Jupiter](https://junit.org/junit5/) - Unit and integration testing
* [Spring Boot](https://spring.io/projects/spring-boot) 
* [PostgreSQL](https://www.postgresql.org/)

### Developers
* [Tomasz Czerwiec](https://github.com/Termoss)