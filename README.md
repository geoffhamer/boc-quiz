# boc-quiz

A demonstration project requested by the Bank of Canada to demonstrate proficiency 
in creating a simple Spring Boot application

## Installation

- From this directory (location of README.md) change directories to /boc-quiz
- Use Maven to build the project	

```bash
mvn clean install
```

## Testing

A number of MVC tests have been included in the project. These can be run using Maven as follows:

```bash
mvn test
```

## Usage

Copy the jar file from the target directory to folder of your choice {/boc-quiz/target/boc-quiz.jar] 
and execute it using the following command

```bash
java -jar boc-quiz.jar <location of .csv file>
```

For Example, if the .csv file is located in the same directory as the .jar file:

```bash
java -jar boc-quiz.jar eng-climate-summary.csv 
```

Once the Spring Boot applications starts, point your browser to:

```bash
http://localhost:8080
```

## Errors

The City Climate Detail page will display an error if the station requested cannot be found. 
To create this error, try one of the following URLs:

```bash
http://localhost:8080/detail?index=abcd
http://localhost:8080/detail?index=2000
```

## Logging

The application will create a boc-quiz.log file in the same folder that it is run from. The logging 
level for the application is set to TRACE

## Requirements

- java jdk-11.0.8
- Maven 3.6.3


