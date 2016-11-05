# UI #

## Starting UI ##
grunt serve

## Full Build ##
grunt

# Server #

## Starting Server ##
./gradlew clean run -it

## Kill server (windows) ##
netstat -o -n -a | findstr 0.0:5050
taskkill /F /PID <pid>


# Heroku #

https://jobmine-app.herokuapp.com/index.html