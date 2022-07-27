# SkyView Backend

SkyView is an online shop dedicated to selling photos of our beautiful sky. With SkyView, people are able to easily view and purchase photos while making it fun!

[_Click here to view the official SkyView frontend_](https://github.com/Revature-Capstone-1350/e-commerce-frontend)

[![Java CI Workflow](https://github.com/Revature-Capstone-1350/e-commerce-backend/actions/workflows/workflowAPI.yml/badge.svg)](https://github.com/Revature-Capstone-1350/e-commerce-backend/actions/workflows/workflowAPI.yml)
# About SkyView

SkyView is a project written by Revature's May 9th, 2022 batch. As a *first sprint*, it contains a functional e-commerce backend. Users are able to:

- Register and login to an account
- View and edit their user profile
- View products and add them to their cart
- Checkout their products

User information is stored in a PostgreSQL v10 database. User passwords and login tokens used secure algorithms considered uncrackable by modern technology.

### SkyView's backend is written using the following technologies:
- OpenJDK v8 - *open-source implementation of Java 8 SE*
- Apache Maven v3.8.6 - *build automation tool for Java*
- Spring Boot v2.5.8 - *used to quickly build Spring applications*
- H2 v1.4.2 - *embedded SQL database used to test applications*
- PostgreSQL v42.2.24 - *driver used to talk to PostgreSQL databases*
- JUnit v4.13.2 - *testing framework supporting unit and integration tests*
- JJWT v0.11.5 - *open-source "pure Java" implementation of JSON web tokens*
- Lombok v1.18.22 - *library used to reduce boiler-plate code*
- OpenAPI v1.6.9 - *implementation of industry standard API design*

# Building SkyView

There are two official ways to build SkyView.

## Configuration

Before building, ensure that the `application.yml` file is configured properly. Here are a few pointers to ensure that everything is configured:
- Ensure that your PostgreSQL database is up and running before starting the backend.
- Ensure the credentials to the database are correct.
- Ensure that the token secret is at least 256 bits in length and that the RSA encryption/decryption keys are unique.

If you require help, have a look at the provided example `application-local.yml` file.

## Building with Dockerfile

Building with the Dockerfile requires the following:
- Apache Maven 3.8.6 (if you don't have Maven, download it [here](https://maven.apache.org/download.cgi) or install it using your package manager) 

You have the option to build SkyView's backend using the supplied Dockerfile. In order to use it, navigate to the root of the repository, and run `mvn clean package`, followed by `docker build -t . skyview` and `docker run -d skyview --name <container-name>`. Doing so will containerize and then launch the backend on `<container-ip>:5000`. Find the IP of your container using the output of `docker inspect <container-name> | grep IPAddress`. This is a good way to simply run this if you do not intend on modifying it; nothing else is required. You can also use the provided shell script to automate this (`create-container.sh`). 


## Building from source

Building from source requires the following:
- OpenJDK v8 (see below for further details)
- Apache Maven 3.8.6 

### Note: Although SkyView was built and tested with OpenJDK in mind, another JDK (such as Corretto and OracleJDK) will likely work; however, it is best to stick with OpenJDK for the best chance of compatibility.

After cloning from the repository, navigate to the root and run `mvn clean spring-boot:run -Dspring.profiles.active=local`. If you have set up the `application.yml` correctly, the application will run on `localhost:5000`. 

