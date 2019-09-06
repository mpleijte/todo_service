# todo_service

## Howto run?

### 1. Create/Update property files for each environment you like to use <br>
application-dev.properties <br>
application-prod.properties <br>
... etc

### Running gradle project in IntelliJ
 * Run > edit configuration <br>
\> Gradle project: \<<i>path to current to-do project<i>\> <br>
\> Tasks: <b>clean build bootRun</b> <br>
\> VM options: <b>-Dspring.profiles.active=dev</b> <br>

or

### Run from command line
\> gradle gradle bootRun "-Dspring.profiles.active=dev" <br>
\> gradle gradle bootRun "-Dspring.profiles.active=prod" <br>






