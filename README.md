# industrial-development-tools
***
## How to build
Open Terminal/CMD and run next command:
```bash
$ gradlew build
```
You see file `Lab-${version}.jar` in `build/` dir

## How to run
 * Create BD with name `testdb` and set port `:5432` on locahost
 * Open dir `build/libs/` in Terminal/CMD and run command
```bash
$ java -jar Lab-1.0-SNAPSHOT.jar
```

## What methods is implemented?
RESTful architecture!

 - Create/Read/Update/Replace/Delete User entity (GET, POST, DELETE)
 - Create/Read/Update/Replace/Delete GroupProduct entity (GET, POST, DELETE)
 - Create/Read/Update/Replace/Delete Product entity (GET, POST, DELETE)

See for more [information](https://www.restapitutorial.com/lessons/httpmethods.html)