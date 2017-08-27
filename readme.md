# Codecacao Test Application
        _   _         _  _          _   __ _
       | | | |  ___  | || |  ___   | | / /(_)  _     _
       | |_| | / _ \ | || | / _ \  | |/ /  _ _| |_ _| |_  _  _
       |  _  |/ /_\ \| || |/ / \ \ |   /  | |_   _|_   _|| |/ /
       | | | |\ ,___/| || |\ \_/ / | |\ \ | | | |_  | |_ | / /
       |_| |_| \___/ |_||_| \___/  |_| \_\|_| \___| \___||  /
                              _           _              / /
                             / \_______ /|_\             \/
                            /          /_/ \__
                           /             \_/ /
                         _|_              |/|_
                         _|_  O    _    O  _|_
                         _|_      (_)      _|_
                          \                 /
                           _\_____________/_
                          /  \/  (___)  \/  \
                          \__(  o     o  )__/ 

A project developed as a part of a job interview.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- Installed Java 8 or above
- Installed Maven v3 or above

### Installing

1. Open a command terminal
2. Position yourself in the root folder of the project ("Codecacao")
3. Package project with command "mvn package"
4. Run the resulting java file with command "java -jar target/codecacao-rest-service-0.1.0.jar"

The REST service is now available on localhost and it loads dummy data so it can be used right away. To give it a try open a browser window and enter following URL: "http://localhost:8080/api/v1/users?page=1&size=10&sort=type,asc". The output is a JSON representing the first 10 users in ascending order.

Note: Port 8080 is the default. You can change it in the configuration file.

## Running the tests

1. Open a command terminal
2. Position yourself in the root folder of the project ("Codecacao")
3. Execute tests with command "mvn test"

The terminal output gives the status of the test execution.

## Deployment

This is a self-contained project and it can be deployed just by running the codecacao-rest-service-0.1.0.jar on the desired machine.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Srđan Popara**

## License

This project is licensed under the Hello Kitty License.

## Acknowledgments

* Spring Boot tutorials, forums, and stack overflow... Google basically :)
