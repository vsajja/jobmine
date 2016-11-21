# The *simple* but complicated stack#

UI: 

Angular JS 1.x
Restangular
angular-xeditable
ui.bootstrap
Bootstrap
HTML
CSS
Javascript

UI build:
Grunt
Bower

Server:
Java
Groovy
Ratpack
Gradle
JOOQ

Database:
Postgres

Test:
Spock

Collaboration:
Slack

Deployment:
Heroku

# UI #

## Setup UI dev ##
TODO: document this badly!
npm

## Starting UI ##
grunt serve

## Full Build ##
grunt

# Server #

## Server UI dev ##

TODO: document this badly!

## Starting Server ##
./gradlew clean run -it

## Kill server (on windows) ##
netstat -o -n -a | findstr 0.0:5050
taskkill /F /PID <pid>

# Website #
www.jobmine.ca/index.html

# Heroku #

https://jobmine-app.herokuapp.com/index.html

## Deploy ##
TODO:
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

# Report #

UI start time: 206ms
Server startup time: 11.044 seconds

UI change: < 10ms
Server change: 8.562 seconds

Total Lines of Code: 20175 total

