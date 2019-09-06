# todo_service


Running gradle project in IntelliJ

* Create property files for each environment you like to use <br>
e.g.: application-dev.properties <br>
 
* Run > edit configuration <br>
\> Gradle project: \<<i>path to current to-do project<i>\> <br>
\> Tasks: <b>clean build bootRun</b> <br>
\> VM options: <b>-Dspring.profiles.active=dev</b> <br>

Run from command line
e.g.<br>
\> gradle gradle bootRun "-Dspring.profiles.active=dev" <br>
\> gradle gradle bootRun "-Dspring.profiles.active=prod" <br>






