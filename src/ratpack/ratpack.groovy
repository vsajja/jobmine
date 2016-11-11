import com.zaxxer.hikari.HikariConfig
import groovy.json.JsonSlurper
import groovy.xml.XmlUtil
import jooq.generated.tables.daos.CompanyDao
import jooq.generated.tables.daos.JobDao
import jooq.generated.tables.daos.JobMineDao
import jooq.generated.tables.daos.SchoolDao
import jooq.generated.tables.daos.StudentDao
import jooq.generated.tables.pojos.Company
import jooq.generated.tables.pojos.Job
import jooq.generated.tables.pojos.JobMine
import jooq.generated.tables.pojos.School
import jooq.generated.tables.pojos.Student
import jooq.generated.tables.records.CompanyRecord
import jooq.generated.tables.records.JobMineRecord
import jooq.generated.tables.records.JobRecord
import jooq.generated.tables.records.SchoolRecord
import jooq.generated.tables.records.StudentRecord
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.config.ConfigData
import ratpack.config.ConfigDataBuilder
import ratpack.groovy.sql.SqlModule
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.http.Status
import ratpack.http.client.HttpClient
import org.jobmine.postgres.PostgresConfig
import org.jobmine.postgres.PostgresModule
import org.jobmine.redis.RedisConfig

import javax.sql.DataSource
import java.sql.Date
import java.text.NumberFormat
import java.text.SimpleDateFormat

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.jsonNode

import static jooq.generated.Tables.*;

final Logger log = LoggerFactory.getLogger(this.class)

ratpack {
    bindings {
        final ConfigData configData = ConfigData.of { ConfigDataBuilder builder ->
            builder.props(
                    ['postgres.user'        : 'zoqyxwbxjuntzp',
                     'postgres.password'    : 'sfXLiNPmZuRUxS_biBDNyNRhDh',
                     'postgres.portNumber'  : 5432,
                     'postgres.databaseName': 'd4ogiv1q9mi0tp',
                     'postgres.serverName'  : 'ec2-54-243-249-65.compute-1.amazonaws.com',
                     'redis.host'           : 'pub-redis-19472.us-east-1-2.5.ec2.garantiadata.com',
                     'redis.portNumber'     : 19472,
                     'redis.password'       : 'sfXLiNPmZuRUxS_biBDNyNRhDh'])
            builder.build()
        }

        bindInstance PostgresConfig, configData.get('/postgres', PostgresConfig)
        bindInstance RedisConfig, configData.get('/redis', RedisConfig)

        module HikariModule, { HikariConfig config ->
            config.dataSource =
                    new PostgresModule().dataSource(
                            configData.get('/postgres', PostgresConfig))
        }
        module SqlModule
    }

    handlers {
        all RequestLogger.ncsa(log)

        get {
            render 'hello dad'
        }


        prefix('api/v1') {
            path('jobmine') {
                byMethod {
                    get {
                        render 'hello pleb'
                    }
                }
            }

            path('mines') {
                byMethod {
                    post {
                        parse(jsonNode()).map { params ->
                            log.info(params.toString())
                            def name = params.get('name').textValue()

                            assert name

                            DataSource dataSource = registry.get(DataSource.class)
                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)

                            JobMineRecord record = context
                                    .insertInto(JOB_MINE)
                                    .set(JOB_MINE.NAME, name)
                                    .returning(JOB_MINE.JOB_MINE_ID)
                                    .fetchOne()

                            println "created job_mine with id: " + record.getValue(JOB_MINE.JOB_MINE_ID)
                        }.then {
                            response.send()
                        }
                    }
                }
            }
            path('companies') {
                byMethod {
                    post {
                        parse(jsonNode()).map { params ->
                            log.info(params.toString())
                            def name = params.get('name').textValue()
                            def description = params.get('description').textValue()
                            def website_url = params.get('website_url').textValue()
                            def total_employees = params.get('total_employees').intValue()
                            def industry = params.get('industry').textValue()
                            def founded_date = new java.sql.Date(
                                    new SimpleDateFormat("yyyy-MM-dd").parse(
                                            params.get('founded_date').textValue()).getTime())

                            assert name
                            assert description
                            assert website_url
                            assert total_employees
                            assert industry
                            assert founded_date

                            DataSource dataSource = registry.get(DataSource.class)
                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)

                            CompanyRecord record = context
                                    .insertInto(COMPANY)
                                    .set(COMPANY.NAME, name)
                                    .set(COMPANY.DESCRIPTION, description)
                                    .set(COMPANY.WEBSITE_URL, website_url)
                                    .set(COMPANY.TOTAL_EMPLOYEES, total_employees)
                                    .set(COMPANY.INDUSTRY, industry)
                                    .set(COMPANY.FOUNDED_DATE, founded_date)
                                    .returning(COMPANY.COMPANY_ID)
                                    .fetchOne()

                            println "created company with id: " + record.getValue(COMPANY.COMPANY_ID)
                        }.then {
                            response.send()
                        }
                    }
                }
            }
            path('jobs') {
                byMethod {
                    get {
                        render 'jobs'
                    }

                    post {
                        parse(jsonNode()).map { params ->
                            log.info(params.toString())
                            def title = params.get('title').textValue()
                            def description = params.get('description').textValue()
                            def type = params.get('type').textValue()
                            def status = params.get('status').textValue()
                            def total_openings = params.get('total_openings').intValue()
                            def created_timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())

                            assert title
                            assert description
                            assert type
                            assert status
                            assert total_openings
                            assert created_timestamp

                            DataSource dataSource = registry.get(DataSource.class)
                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
                            JobRecord record = context
                                    .insertInto(JOB)
                                    .set(JOB.TITLE, title)
                                    .set(JOB.DESCRIPTION, description)
                                    .set(JOB.TYPE, type)
                                    .set(JOB.STATUS, status)
                                    .set(JOB.TOTAL_OPENINGS, total_openings)
                                    .set(JOB.CREATED_TIMESTAMP, created_timestamp)
                                    .returning(JOB.JOB_ID)
                                    .fetchOne()

                            println "created job with id: " + record.getValue(JOB.JOB_ID)
                        }.then {
                            response.send()
                        }
                    }
                }
            }
            path('schools') {
                byMethod {
                    post {
                        parse(jsonNode()).map { params ->
                            log.info(params.toString())
                            def name = params.get('name').textValue()
                            def type = params.get('type').textValue()
                            def total_students = params.get('total_students').intValue()
                            def established_date = new java.sql.Date(
                                    new SimpleDateFormat("yyyy").parse(
                                            params.get('established_date').textValue()).getTime())
                            def description = params.get('description').textValue()

                            assert name
                            assert type
                            assert total_students
                            assert established_date
                            assert description

                            DataSource dataSource = registry.get(DataSource.class)
                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
                            SchoolRecord record = context
                                    .insertInto(SCHOOL)
                                    .set(SCHOOL.NAME, name)
                                    .set(SCHOOL.TYPE, type)
                                    .set(SCHOOL.TOTAL_STUDENTS, total_students)
                                    .set(SCHOOL.ESTABLISHED_DATE, established_date)
                                    .set(SCHOOL.DESCRIPTION, description)
                                    .returning(SCHOOL.SCHOOL_ID)
                                    .fetchOne()

                            println "created school with id: " + record.getValue(SCHOOL.SCHOOL_ID)
                        }.then {
                            response.send()
                        }
                    }
                }
            }
            path('students') {
                byMethod {
                    post {
                        parse(jsonNode()).map { params ->
                            log.info(params.toString())
                            def first_name = params.get('first_name').textValue()
                            def last_name = params.get('last_name').textValue()
                            def username = params.get('username').textValue()
                            def email_address = params.get('email_address').textValue()
                            def employment_status = params.get('employment_status').textValue()
                            def karma = params.get('karma').intValue()
                            def total_views = params.get('total_views').intValue()
                            def age = params.get('age').intValue()
                            def gender = params.get('gender').textValue()
                            def salary = params.get('salary').intValue()
                            def relationship_status = params.get('relationship_status').textValue()
                            def dreams = params.get('dreams').textValue()
                            def phone_number = params.get('phone_number').textValue()
                            def employment_history = params.get('employment_history').textValue()
                            def skills = params.get('skills').textValue()
                            def last_loggedin_timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())
                            def joined_timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())

                            assert first_name
                            assert last_name
                            assert username
                            assert email_address
                            assert employment_status
                            assert karma
                            assert total_views
                            assert age
                            assert gender
                            assert salary
                            assert relationship_status
                            assert dreams
                            assert phone_number
                            assert employment_history
                            assert skills
                            assert last_loggedin_timestamp
                            assert joined_timestamp

                            DataSource dataSource = registry.get(DataSource.class)
                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
                            StudentRecord record = context
                                    .insertInto(STUDENT)
                                    .set(STUDENT.FIRST_NAME, first_name)
                                    .set(STUDENT.LAST_NAME, last_name)
                                    .set(STUDENT.USERNAME, username)
                                    .set(STUDENT.EMAIL_ADDRESS, email_address)
                                    .set(STUDENT.EMPLOYMENT_STATUS, employment_status)
                                    .set(STUDENT.KARMA, karma)
                                    .set(STUDENT.TOTAL_VIEWS, total_views)
                                    .set(STUDENT.AGE, age)
                                    .set(STUDENT.GENDER, gender)
                                    .set(STUDENT.SALARY, salary)
                                    .set(STUDENT.RELATIONSHIP_STATUS, relationship_status)
                                    .set(STUDENT.DREAMS, dreams)
                                    .set(STUDENT.PHONE_NUMBER, phone_number)
                                    .set(STUDENT.EMPLOYMENT_HISTORY, employment_history)
                                    .set(STUDENT.SKILLS, skills)
                                    .set(STUDENT.LAST_LOGGEDIN_TIMESTAMP, last_loggedin_timestamp)
                                    .set(STUDENT.JOINED_TIMESTAMP, joined_timestamp)
                                    .returning(STUDENT.STUDENT_ID)
                                    .fetchOne()
//
                            println "created student with id: " + record.getValue(STUDENT.STUDENT_ID)
                        }.then {
                            response.send()
                        }
                    }
                }
            }

            /*
            get('jobs') {
                response.headers.add('Access-Control-Allow-Origin', '*')
                DataSource dataSource = registry.get(DataSource.class)
                Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
                List<Job> jobPostings = new JobDao(configuration).findAll()
                render json(jobPostings)
            }
            get('schools') {
                response.headers.add('Access-Control-Allow-Origin', '*')
                DataSource dataSource = registry.get(DataSource.class)
                Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
                List<School> schools = new SchoolDao(configuration).findAll()
                render json(schools)
            }
            get('students') {
                response.headers.add('Access-Control-Allow-Origin', '*')
                DataSource dataSource = registry.get(DataSource.class)
                Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
                List<Student> students = new StudentDao(configuration).findAll()
                render json(students)
            }
            get('companies') {
                response.headers.add('Access-Control-Allow-Origin', '*')
                DataSource dataSource = registry.get(DataSource.class)
                Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
                List<Company> companies = new CompanyDao(configuration).findAll()
                render json(companies)
            }
            */
        }

//        prefix('test') {
//            prefix('data') {
//                get('jobs') {
//                    DataSource dataSource = registry.get(DataSource.class)
//                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
//
//                    HttpClient httpClient = registry.get(HttpClient.class)
//
//                    URI uri = new URI('http://api.indeed.com/ads/apisearch' +
//                            '?publisher=6453215428478291' +
//                            '&v=2' +
//                            '&format=json' +
//                            '&limit=25' +
//                            '&q=software' +
//                            '&l=Waterloo' +
//                            '&co=ca'
//                    )
//
//                    httpClient.get(uri).then {
//                        def root = new JsonSlurper().parseText(it.body.text)
//
//                        // get total results
//                        int totalResults = root.totalResults
//                        log.info(totalResults.toString())
//
//                        1.step(totalResults, 25) {
//                            httpClient.get(uri).then { response ->
//                                root = new JsonSlurper().parseText(response.body.text)
//                                root.results.each {
//                                    if (!it.expired) {
//                                        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm:ss z");
////                                    log.info("Inserting: ${it.jobtitle.toString()} @ ${it.company.toString()}")
////                                    create.insertInto(JOB)
////                                            .set(JOB.TITLE, it.jobtitle.toString())
////                                            .set(JOB.EMPLOYERNAME, it.company.toString())
////                                            .set(JOB.DESCRIPTION_9, it.snippet.toString())
////                                            .set(JOB.DATEPOSTED_9, new java.sql.Date(formatter.parse(it.date.toString()).getTime()))
////                                            .set(JOB.LOCATION, it.formattedLocation.toString())
////                                            .execute()
//                                    }
//                                }
//                            }
//                        }
//                        render totalResults.toString()
//                    }
//                }
//                get('students') {
//                    def studentFile = new File('src/ratpack/data/students_generated_200.txt')
//
//                    def students = []
//
//                    studentFile.text.eachLine { line ->
//
//                        if (!line.startsWith('name')) {
//                            def studentData = line.tokenize('|')
//
//                            def name = studentData[0]
//                            def phoneNumber = studentData[1]
//                            def email = studentData[2]
//                            def dateJoined = studentData[3]
//                            def streetAddress = studentData[4]
//                            def city = studentData[5]
//                            def region = studentData[6]
//                            def country = studentData[7]
//                            def postalOrZip = studentData[8]
//                            def description = studentData[9]
//                            def age = studentData[10]
//
////                        log.info("Inserting: $name")
////                        DataSource dataSource = registry.get(DataSource.class)
////                        DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
////                        create.insertInto(STUDENT)
////                                .set(STUDENT.NAME, name)
////                                .set(STUDENT.PHONENUMBER, phoneNumber)
////                                 // TODO: date joined
////                                .set(STUDENT.DATEJOINED, new Date(Calendar.getInstance().getTimeInMillis()))
////                                .set(STUDENT.EMAIL, email)
////                                .set(STUDENT.STREETADDRESS, streetAddress)
////                                .set(STUDENT.CITY, city)
////                                .set(STUDENT.REGION, region)
////                                .set(STUDENT.COUNTRY, country)
////                                .set(STUDENT.POSTALORZIP, postalOrZip)
////                                .set(STUDENT.DESCRIPTION, description)
////                                .set(STUDENT.AGE, Integer.parseInt(age))
////                                .execute()
//                        }
//                    }
//
//                    render students.toString()
//                }
//                prefix('schools') {
//                    get('unis/canada') {
//                        def htmlFile = new File('src/ratpack/data/schools_canada_universities_wikipedia.html')
//                        def html = new XmlSlurper().parse(htmlFile)
//
//                        html.tbody.children().each { tr ->
//                            tr.collect {
//
//                                def name = it.td[0].depthFirst().findAll { it.name() == 'a' }[0].@title.toString()
//                                def schoolType = 'University'
//                                def city = it.td[1].toString().replaceAll("[\\t\\n\\r]", " ").replaceAll("\\s+", " ")
//                                def provinceOrState = it.td[2].toString()
//                                def country = 'Canada'
//                                def established = it.td[4].toString()
//                                def totalStudents = (int) NumberFormat.getIntegerInstance(Locale.US).parse(it.td[7].localText()[0].toString())
//                                def wikiLink = 'https://en.wikipedia.org/wiki/List_of_universities_in_Canada' + it.td[0].depthFirst().findAll {
//                                    it.name() == 'a'
//                                }[0].@href.toString()
//                                def logoSrc = null
//
////                            log.info("Inserting: $name @ $city , $provinceOrState, $country")
////                            DataSource dataSource = registry.get(DataSource.class)
////                            DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
////                            create.insertInto(SCHOOL)
////                                    .set(SCHOOL.NAME, name)
////                                    .set(SCHOOL.SCHOOLTYPE, schoolType)
////                                    .set(SCHOOL.CITY, city)
////                                    .set(SCHOOL.PROVINCEORSTATE, provinceOrState)
////                                    .set(SCHOOL.COUNTRY, country)
////                                    .set(SCHOOL.ESTABLISHED, established)
////                                    .set(SCHOOL.TOTALSTUDENTS, totalStudents)
////                                    .set(SCHOOL.WIKILINK, wikiLink)
////                                    .set(SCHOOL.LOGOSRC, 'images/icon_default_company.png')
////                                    .execute()
//                            }
//                        }
//                        render htmlFile.text
//                    }
//                }
//                prefix('companies') {
//                    get('canada') {
////                    def wikiLinks = new File('src/ratpack/data/companies_canada_wiki_link.txt')
//                        def wikiLinks = new File('src/ratpack/data/companies_canada_wiki_links.txt')
//
//                        def something = 'something'
//
//                        wikiLinks.eachLine { wikiLink ->
//                            wikiLink = wikiLink.replaceAll("<li><a href=\"", '').split("\"")[0]
//                            wikiLinkName = 'TODO'
//
//                            HttpClient httpClient = registry.get(HttpClient.class)
//                            URI uri = new URI('https://en.wikipedia.org' + wikiLink)
//
//                            httpClient.get(uri).then {
//                                log.info("Parsing: $wikiLink")
//                                def wikiHtml = XmlUtil.serialize(it.body.text)
//                                def infoBoxExists = false
//                                def inInfoBox = false
//                                def infoBoxHtml = ''
//                                wikiHtml.eachLine { line ->
//                                    if (line.contains('infobox ') && line.contains('<table')) {
//                                        inInfoBox = true
//                                        infoBoxExists = true
//                                    }
//                                    if (line.contains('</table>')) {
//                                        inInfoBox = false
//                                    }
//                                    if (inInfoBox) {
//                                        infoBoxHtml += line
//                                    }
//                                }
//                                if (infoBoxExists) {
//                                    infoBoxHtml += "</table>"
////                                log.info(infoBoxHtml)
//                                    try {
//                                        def slurper = new XmlSlurper()
//                                        def infoBox = slurper.parseText(infoBoxHtml)
//
//                                        // parse name
//                                        def name = infoBox.children().depthFirst().findAll {
//                                            it.@class.toString() == 'fn org'
//                                        }[0]?.text()
//                                        if (!name) {
//                                            name = infoBox.caption?.text()
//                                        }
//                                        if (!name) {
//                                            name = wikiLinkName
//                                        }
//                                        // parse logo url
//                                        def logoUrl = infoBox.children().depthFirst().findAll {
//                                            it.@class.toString() == 'logo'
//                                        }[0]?.a.img.@src
//
//                                        log.info("Name: \t\t\t" + name.toString())
//                                        log.info(logoUrl.toString())
//                                        assert name
//                                        assert logoUrl
//
////                                        log.info("Inserting: $name")
////                                        DataSource dataSource = registry.get(DataSource.class)
////                                        DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
////                                        create.insertInto(COMPANY)
////                                                .set(COMPANY.NAME, name.toString())
////                                                .set(COMPANY.LOGOURL, logoUrl.toString())
////                                                .execute()
//                                    }
//                                    catch (Exception e) {
//                                        log.info("ERROR - ${e.getMessage()}")
//                                    }
//                                } else {
//                                    log.info("SKIPPING $wikiLink, missing infoBox")
//                                }
//                            }
//                        }
//                        render something
//                    }
//                }
//            }
//
//            prefix('insert') {
//                get('jobs') {
//                    DataSource dataSource = registry.get(DataSource.class)
//                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
//                    create.insertInto(JOB)
//                            .set(JOB.TITLE, 'Software Developer II')
//                            .set(JOB.EMPLOYERNAME, 'BlackBerry')
//                            .set(JOB.DESCRIPTION_9, 'Description')
//                            .set(JOB.DATEPOSTED_9, new Date(123))
//                            .set(JOB.LOCATION, 'Waterloo')
//                            .execute()
//                    render "inserted!"
//                }
//                get('schools') {
//                    DataSource dataSource = registry.get(DataSource.class)
//                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
//                    create.insertInto(SCHOOL)
//                            .set(SCHOOL.NAME, 'University of Waterloo')
//                            .set(SCHOOL.SCHOOLTYPE, 'University')
//                            .set(SCHOOL.CITY, 'Waterloo')
//                            .set(SCHOOL.PROVINCEORSTATE, 'Ontario')
//                            .set(SCHOOL.COUNTRY, 'Canada')
//                            .set(SCHOOL.ESTABLISHED, '1999')
//                            .set(SCHOOL.TOTALSTUDENTS, 1)
//                            .set(SCHOOL.WIKILINK, 'wiki/UniversityOfWaterloo')
//                            .set(SCHOOL.LOGOSRC, 'dist/images/icon_default_company.578524b6.png')
//                            .execute()
//                    render "inserted!"
//                }
//                get('students') {
//                    DataSource dataSource = registry.get(DataSource.class)
//                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
//                    create.insertInto(STUDENT)
//                            .set(STUDENT.NAME, 'Vinod Sajja')
//                            .set(STUDENT.PHONENUMBER, '(519) 502-7991')
//                            .set(STUDENT.DATEJOINED, new Date(Calendar.getInstance().getTimeInMillis()))
//                            .set(STUDENT.EMAIL, 'vsajja@engmail.uwaterloo.ca')
//                            .set(STUDENT.STREETADDRESS, '123 Fake Street, Unit 35')
//                            .set(STUDENT.CITY, 'Waterloo')
//                            .set(STUDENT.REGION, 'Ontario')
//                            .set(STUDENT.COUNTRY, 'Canada')
//                            .set(STUDENT.POSTALORZIP, 'T9H5R7')
//                            .set(STUDENT.DESCRIPTION, 'description')
//                            .set(STUDENT.AGE, Integer.parseInt('29'))
//                            .execute()
//                    render "inserted!"
//                }
//                get('companies') {
//                    DataSource dataSource = registry.get(DataSource.class)
//                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);
//                    create.insertInto(COMPANY)
//                            .set(COMPANY.NAME, 'JobMine')
//                            .set(COMPANY.LOGOURL, 'images/logo_jobmine.png')
//                            .execute()
//                    render "inserted!"
//                }
//            }
//        }

        files {
            dir 'dist'
        }
    }
}