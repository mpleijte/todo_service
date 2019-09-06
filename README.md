# todo_service

## Howto setup environments?
##### Create/Update .properties files for each environment you would like to use <br>
For example: add following snippet to file: _application-dev.properties_ \* <br> 
(\* create the file if it doesn't exists)
``` 
# Security
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=USER
```


## Howto run this gradle project?
#### Using Intellij
1. Run > edit configuration <br>
2. Gradle project: \<<i>path to current to-do project</i>\> <br>
3. Tasks: <b>build bootRun</b> <br>
4. VM options: <b>-Dspring.profiles.active=dev</b> <br>
5. Click button: OK
6. Click green run icon, this starts your newly created runconfiguration
#### Using command line
\> gradle bootRun "-Dspring.profiles.active=dev" <br>




