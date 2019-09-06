# todo_service

### Howto setup environments?
#### Create/Update .properties files for each environment you would like to use <br>
_FOR EXAMPLE_ <br>
To set global username/password/roles add these properties to file: "application-dev.properties"
```
# Security
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=USER
```

### Howto run this gradle project?
#### Using Intellij
 * Run > edit configuration <br>
\> Gradle project: \<<i>path to current to-do project</i>\> <br>
\> Tasks: <b>clean build bootRun</b> <br>
\> VM options: <b>-Dspring.profiles.active=dev</b> <br>


#### Using command line
\> gradle gradle bootRun "-Dspring.profiles.active=dev" <br>
\> gradle gradle bootRun "-Dspring.profiles.active=prod" <br>






