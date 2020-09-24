# boc-quiz

A demonstration project requested by the Bank of Canada to demonstrate proficiency 
in creating a simple Spring Boot application

## Installation

- From this directory (location of README.md) change directories to /boc-quiz
- Use Maven to build the project	

```bash
mvn clean install
```

## Usage

Copy the jar file from the target directory to folder of your choice {/boc-quiz/target/boc-quiz.jar] 
and execute it using the following command

```bash
java -jar boc-quiz.jar <location of .csv file>
```

Once the Spring Boot applications starts, point your browser to:

```bash
http://localhost:8080
```

## Requirements

- java jdk-11.0.8
- Maven 3.6.3


