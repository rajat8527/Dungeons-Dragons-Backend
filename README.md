# Rakuten Technical Challenge

This project is a part of Rakuten Technical Assessment Test. 

### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* [git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* [Open API Swagger] - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

## Points covered as per the given tasks

- API Endpoints created for fetching and saving data from database.
- Spring Cache has been used to implement the caching.
- MongoDB (Cloud) has been used for database layer.
- Travis CI integration has been added.
- Heroku integration added
- Code published on Github, Travis CI and Heroku.
- README.md file added.

## Bonus points added
- Open API Swagger integration added
- New Relic APM integration added
- Docker integration added
- Implemented Custom and Global Exception handling. 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.rakuten.Application` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Usage

* Get Character Data API
  -   Endpoint => localhost:9908/api/getCharacterData
  -   Request Type => GET

* Save Character Data API
  -   Endpoint => localhost:9908/api/saveCharacterData
  -   Request Type => POST
  -   Example Input 
      - {
        	"name":"goku",
        	"age": 65,
        	"classes": "barbarian",
        	"races":"dragonborn"
        }


* Delete All Data API
  -   Endpoint => localhost:9908/api/deleteAllData
  -   Request Type => DELETE
                
* [Swagger](http://localhost:9908/swagger-ui.html) - Swagger Open API Documentation

## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.rakuten│
│           ├── com.rakuten.constants
│           ├── com.rakuten.controller
│           ├── com.rakuten.dto
│           ├── com.rakuten.model
│           ├── com.rakuten.exception
│           └── com.rakuten.repository
│           └── com.rakuten.service
│           └── com.rakuten.serviceimpl
├── src
│   └── main
│       └── resources
│           ├── application.yml
├── JRE System Library
├── Maven Dependencies
├── src
├── pom.xml
└── README.md
```

## Packages

- `controller` -  to listen to the client;
- `exception` -  to define custom and global exception classes;
- `model` -  to hold model classes or POJO;
- `dto` -  to hold data transfer objects;
- `security`  -  to secure the api endpoints;
- `service`  -  to define our business logic;
- `serviceimpl` -  to implement our business logic;
- `constants` - to define application constants;
- `resources/` - Contains all the static resources, templates and property files.
- `resources/application.yml` - It contains application-wide properties. Spring reads the properties defined in this file to configure our application. We can define server’s default port, server’s context path, database URLs etc, in this file.
- `pom.xml` - contains all the project dependencies

## Additional notes

- Used Lombok library for the generation of Getter, Setters and Default Constructors in Model Classes.
- Third Party APIs Used are,
  - http://www.dnd5eapi.co/api
