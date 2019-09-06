# todo_service

## Howto run?

### Create/Update .properties files for each environment you would like to use <br>

for example, to set global username/password/roles add these properties to file: "application-dev.properties"
```# Security
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=USER```



### Running gradle project in IntelliJ
 * Run > edit configuration <br>
\> Gradle project: \<<i>path to current to-do project</i>\> <br>
\> Tasks: <b>clean build bootRun</b> <br>
\> VM options: <b>-Dspring.profiles.active=dev</b> <br>

or

### Run from command line
\> gradle gradle bootRun "-Dspring.profiles.active=dev" <br>
\> gradle gradle bootRun "-Dspring.profiles.active=prod" <br>






