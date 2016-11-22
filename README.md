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
TODO:

## Starting UI ##
grunt serve

## Full Build ##
grunt

# Server #

## Server UI dev ##

TODO:

## Starting Server ##
./gradlew clean run -it

## Kill server (on windows) ##
netstat -o -n -a | findstr 0.0:5050
taskkill /F /PID <pid>

# Website #
www.jobmine.ca/index.html

# Heroku #

https://jm-app.herokuapp.com/index.html

## Deploy ##
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)
TODO

# Report #

## UI ##
start: 206ms
change: < 10ms

## Server ##
start: 11.044 seconds
change: 8.562 seconds

## Lines of Code ##
lines: 20175 total
