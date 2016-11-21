# The *simple* but complicated stack that took #

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

Total lines of code: 20,175
Percentage of code that is generated: %70 (estimated)

PLEASE DONT FUCK SHIT UP

$ git ls-files | xargs wc -l
      9 .gitignore
      3 Procfile
     25 README.md
     78 build.gradle
     96 db/backups/nov20/nov20.rar
    114 db/backups/nov21/nov21.rar
    163 db/schema.png
    153 db/schema.sql
    151 db/schema_initial.sql
    359 gradle/wrapper/gradle-wrapper.jar
      6 gradle/wrapper/gradle-wrapper.properties
    160 gradlew
     90 gradlew.bat
      1 settings.gradle
     11 src/main/groovy/org/jobmine/postgres/PostgresConfig.groovy
     31 src/main/groovy/org/jobmine/postgres/PostgresModule.groovy
     12 src/main/groovy/org/jobmine/redis/RedisConfig.groovy
    169 src/main/java/jooq/generated/Keys.java
    103 src/main/java/jooq/generated/Public.java
     85 src/main/java/jooq/generated/Sequences.java
     95 src/main/java/jooq/generated/Tables.java
    167 src/main/java/jooq/generated/tables/Company.java
    141 src/main/java/jooq/generated/tables/Document.java
    137 src/main/java/jooq/generated/tables/Image.java
    172 src/main/java/jooq/generated/tables/Job.java
    136 src/main/java/jooq/generated/tables/JobApp.java
    136 src/main/java/jooq/generated/tables/JobAppPackage.java
    152 src/main/java/jooq/generated/tables/JobInterview.java
    136 src/main/java/jooq/generated/tables/JobMine.java
    147 src/main/java/jooq/generated/tables/JobOffer.java
    137 src/main/java/jooq/generated/tables/Location.java
    162 src/main/java/jooq/generated/tables/School.java
    227 src/main/java/jooq/generated/tables/Student.java
    123 src/main/java/jooq/generated/tables/daos/CompanyDao.java
     87 src/main/java/jooq/generated/tables/daos/DocumentDao.java
     94 src/main/java/jooq/generated/tables/daos/ImageDao.java
     80 src/main/java/jooq/generated/tables/daos/JobAppDao.java
     80 src/main/java/jooq/generated/tables/daos/JobAppPackageDao.java
    130 src/main/java/jooq/generated/tables/daos/JobDao.java
    102 src/main/java/jooq/generated/tables/daos/JobInterviewDao.java
     80 src/main/java/jooq/generated/tables/daos/JobMineDao.java
     95 src/main/java/jooq/generated/tables/daos/JobOfferDao.java
     94 src/main/java/jooq/generated/tables/daos/LocationDao.java
    116 src/main/java/jooq/generated/tables/daos/SchoolDao.java
    207 src/main/java/jooq/generated/tables/daos/StudentDao.java
    125 src/main/java/jooq/generated/tables/pojos/Company.java
     79 src/main/java/jooq/generated/tables/pojos/Document.java
     88 src/main/java/jooq/generated/tables/pojos/Image.java
    134 src/main/java/jooq/generated/tables/pojos/Job.java
     70 src/main/java/jooq/generated/tables/pojos/JobApp.java
     70 src/main/java/jooq/generated/tables/pojos/JobAppPackage.java
     98 src/main/java/jooq/generated/tables/pojos/JobInterview.java
     70 src/main/java/jooq/generated/tables/pojos/JobMine.java
     89 src/main/java/jooq/generated/tables/pojos/JobOffer.java
     88 src/main/java/jooq/generated/tables/pojos/Location.java
    116 src/main/java/jooq/generated/tables/pojos/School.java
    233 src/main/java/jooq/generated/tables/pojos/Student.java
    471 src/main/java/jooq/generated/tables/records/CompanyRecord.java
    259 src/main/java/jooq/generated/tables/records/DocumentRecord.java
    301 src/main/java/jooq/generated/tables/records/ImageRecord.java
    217 src/main/java/jooq/generated/tables/records/JobAppPackageRecord.java
    217 src/main/java/jooq/generated/tables/records/JobAppRecord.java
    345 src/main/java/jooq/generated/tables/records/JobInterviewRecord.java
    217 src/main/java/jooq/generated/tables/records/JobMineRecord.java
    303 src/main/java/jooq/generated/tables/records/JobOfferRecord.java
    513 src/main/java/jooq/generated/tables/records/JobRecord.java
    301 src/main/java/jooq/generated/tables/records/LocationRecord.java
    429 src/main/java/jooq/generated/tables/records/SchoolRecord.java
    975 src/main/java/jooq/generated/tables/records/StudentRecord.java
      0 src/ratpack/data/companies_canada_wiki_link.txt
    545 src/ratpack/data/companies_canada_wiki_links.txt
   1239 src/ratpack/data/schools_canada_universities_wikipedia.html
    200 src/ratpack/data/students_generated_200.txt
    127 src/ratpack/dist/404.html
     10 src/ratpack/dist/favicon.ico
    105 src/ratpack/dist/fonts/glyphicons-halflings-regular.eot
    287 src/ratpack/dist/fonts/glyphicons-halflings-regular.svg
    771 src/ratpack/dist/fonts/glyphicons-halflings-regular.ttf
     93 src/ratpack/dist/fonts/glyphicons-halflings-regular.woff
     72 src/ratpack/dist/fonts/glyphicons-halflings-regular.woff2
     13 src/ratpack/dist/images/icon_hardhat.aac9fa38.png
    148 src/ratpack/dist/images/icon_slack.7eb6ce3d.png
      2 src/ratpack/dist/images/icon_twitch.2ae868f8.png
     14 src/ratpack/dist/images/logo_jobmine.0507cde3.png
     48 src/ratpack/dist/images/student_profile_nobody.29ae7644.jpg
     11 src/ratpack/dist/index.html
      4 src/ratpack/dist/robots.txt
      1 src/ratpack/dist/scripts/scripts.78b0971e.js
     18 src/ratpack/dist/scripts/vendor.2d491e8d.js
      0 src/ratpack/dist/styles/main.d41d8cd9.css
      4 src/ratpack/dist/styles/vendor.44f4b49d.css
    953 src/ratpack/ratpack.groovy
    119 src/test/groovy/org/jobmine/CompanySpec.groovy
    452 src/test/groovy/org/jobmine/JobmineSpec.groovy
     91 src/test/groovy/org/jobmine/SchoolSpec.groovy
    112 src/test/groovy/org/jobmine/StudentSpec.groovy
      3 ui/.bowerrc
     21 ui/.editorconfig
      0 ui/.gitattributes
      5 ui/.gitignore
      6 ui/.jscsrc
     16 ui/.jshintrc
     10 ui/.yo-rc.json
    483 ui/Gruntfile.js
     20 ui/README.md
    151 ui/app/404.html
     10 ui/app/favicon.ico
     26 ui/app/images/icon_hardhat.png
    303 ui/app/images/icon_slack.png
      4 ui/app/images/icon_twitch.png
     13 ui/app/images/icon_under_construction.png
     26 ui/app/images/logo_jobmine.png
    271 ui/app/images/logo_jobmine_pd.pdn
     34 ui/app/images/student_profile_nobody.jpg
    169 ui/app/index.html
      4 ui/app/robots.txt
    128 ui/app/scripts/app.js
     17 ui/app/scripts/controllers/about.js
     24 ui/app/scripts/controllers/companies.js
     35 ui/app/scripts/controllers/company-register.js
     25 ui/app/scripts/controllers/company.js
     17 ui/app/scripts/controllers/developers.js
     40 ui/app/scripts/controllers/job-create.js
     25 ui/app/scripts/controllers/job.js
     25 ui/app/scripts/controllers/jobs.js
     25 ui/app/scripts/controllers/main.js
     17 ui/app/scripts/controllers/mine.js
     25 ui/app/scripts/controllers/mines.js
     17 ui/app/scripts/controllers/register.js
     34 ui/app/scripts/controllers/school-register.js
     25 ui/app/scripts/controllers/school.js
     25 ui/app/scripts/controllers/schools.js
     35 ui/app/scripts/controllers/student-register.js
     25 ui/app/scripts/controllers/student.js
     25 ui/app/scripts/controllers/students.js
      0 ui/app/styles/companies.css
      0 ui/app/styles/company-register.css
      0 ui/app/styles/company.css
      0 ui/app/styles/developers.css
      0 ui/app/styles/index.css
      0 ui/app/styles/job.css
      0 ui/app/styles/jobs.css
      0 ui/app/styles/main.css
      0 ui/app/styles/mine.css
      0 ui/app/styles/mines.css
      0 ui/app/styles/register.css
      0 ui/app/styles/school-register.css
      0 ui/app/styles/school.css
      0 ui/app/styles/schools.css
      0 ui/app/styles/student-register.css
      0 ui/app/styles/student.css
      0 ui/app/styles/students.css
     34 ui/app/views/about.html
     30 ui/app/views/companies.html
     77 ui/app/views/company-register.html
     88 ui/app/views/company.html
     13 ui/app/views/developers.html
     73 ui/app/views/job-create.html
    120 ui/app/views/job.html
     46 ui/app/views/jobs.html
     80 ui/app/views/main.html
      1 ui/app/views/mine.html
     19 ui/app/views/mines.html
     33 ui/app/views/register.html
     88 ui/app/views/school-register.html
     75 ui/app/views/school.html
     39 ui/app/views/schools.html
     93 ui/app/views/student-register.html
    175 ui/app/views/student.html
     32 ui/app/views/students.html
     33 ui/bower.json
      1 ui/copydist.sh
     44 ui/package.json
     18 ui/test/.jshintrc
     86 ui/test/karma.conf.js
     23 ui/test/spec/controllers/about.js
     23 ui/test/spec/controllers/companies.js
     23 ui/test/spec/controllers/company-register.js
     23 ui/test/spec/controllers/company.js
     23 ui/test/spec/controllers/developers.js
     23 ui/test/spec/controllers/job-create.js
     23 ui/test/spec/controllers/job.js
     23 ui/test/spec/controllers/jobs.js
     23 ui/test/spec/controllers/main.js
     23 ui/test/spec/controllers/mine.js
     23 ui/test/spec/controllers/mines.js
     23 ui/test/spec/controllers/register.js
     23 ui/test/spec/controllers/school-register.js
     23 ui/test/spec/controllers/school.js
     23 ui/test/spec/controllers/schools.js
     23 ui/test/spec/controllers/student-register.js
     23 ui/test/spec/controllers/student.js
     23 ui/test/spec/controllers/students.js
  20175 total