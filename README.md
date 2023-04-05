## ENSF 380 Final Project

This program is set up to generate a schedule based on tasks needed to be done throughout the day stored in a database. The database name required of this program will be "treatments". 

A user must be added to the database with the name "oop" and password "password".

First, make sure to be in the directory containing edu and lib folders, then compile the program with the command in the terminal:

For windows
```
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/SQLInfo.java
```
For mac
```
javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/SQLInfo.java
```

Followed by the command:

For windows
```
java -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/SQLInfo
```
For mac
```
java -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/SQLInfo
```

The program will then prompt the user to generate a schedule with a button, if a schedule cannot be made it will prompt them to enter a different start hour of a specific task in order to generate a correct schedule.

If backup volunteers are needed for a specific time, the program will notify user and ask the user to confirm that they were called.

## Halanna Le, Christy Guirguis, Grace Jang, Gillian Habermehl