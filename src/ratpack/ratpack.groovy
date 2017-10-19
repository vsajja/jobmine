import com.zaxxer.hikari.HikariConfig
import groovy.json.JsonSlurper
import jooq.generated.tables.pojos.Company
import jooq.generated.tables.pojos.Document
import jooq.generated.tables.pojos.Job
import jooq.generated.tables.pojos.JobApp
import jooq.generated.tables.pojos.JobAppPackage
import jooq.generated.tables.pojos.JobInterview
import jooq.generated.tables.pojos.JobMine
import jooq.generated.tables.pojos.JobOffer
import jooq.generated.tables.pojos.School
import jooq.generated.tables.pojos.Student
import jooq.generated.tables.records.JobMineRecord
import org.job.JobService
import org.job.exceptions.InvalidCredentialsException
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.form.Form
import ratpack.form.UploadedFile
import ratpack.groovy.sql.SqlModule
import ratpack.handling.RequestLogger
import ratpack.hikari.HikariModule
import ratpack.http.client.HttpClient
import org.job.postgres.PostgresConfig
import org.job.postgres.PostgresModule

import javax.sql.DataSource
import java.text.SimpleDateFormat

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.jsonNode

import static jooq.generated.Tables.*;

final Logger log = LoggerFactory.getLogger(this.class)

ratpack {
    serverConfig {
        props("db.properties")
        require("/postgres", PostgresConfig)
    }

    bindings {
        module HikariModule, { HikariConfig config ->
            config.setMaximumPoolSize(5)
            config.dataSource = new PostgresModule().dataSource(serverConfig.get('/postgres', PostgresConfig))
        }
        module SqlModule
        bind JobService
    }

    handlers { JobService jobService ->
        all RequestLogger.ncsa(log)

        all {
            String forwardedProto = 'X-Forwarded-Proto'
            if (request.headers.contains(forwardedProto)
                    && request.headers.get(forwardedProto) != 'https') {
                redirect(301, "https://${request.headers.get('Host')}${request.rawUri}")
            } else {
                next()
            }
        }

        prefix('api/v1') {
            all {
                response.headers.add('Access-Control-Allow-Origin', '*')
                response.headers.add('Access-Control-Allow-Headers', 'origin, x-requested-with, content-type, Authorization')
                response.headers.add('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS')
                next()
            }

            path('jokes/random') {
                byMethod {
                    get {
                        HttpClient httpClient = registry.get(HttpClient.class)

                        String uriStr = 'http://api.chucknorris.io/jokes/random'

                        String randomJoke

                        httpClient.get(new URI(uriStr)).then {
                            render it.body.text
//                            render new JsonSlurper().parseText(it.body.text).value.toString()
                        }
                    }
                }
            }


            path('jobs') {
                byMethod {
                    get {
                        DataSource dataSource = registry.get(DataSource.class)
                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
                        List<Job> jobs = context.selectFrom(JOB)
                                .fetch()
                                .into(Job.class)
                        render json(jobs)
                    }
                }
            }

            path('jobs/:jobId') {
                def jobId = pathTokens['jobId']
                byMethod {
                    get {
                        DataSource dataSource = registry.get(DataSource.class)
                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
                        Job job = context.selectFrom(JOB)
                                .where(JOB.JOB_ID.equal(jobId))
                                .fetchOne()
                                .into(Job.class)
                        render json(job)
                    }
                }
            }

//            path('job') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//
//                        def schools = context.selectCount().from(SCHOOL).asField('schools')
//                        def students = context.selectCount().from(STUDENT).asField('students')
//                        def companies = context.selectCount().from(COMPANY).asField('companies')
//                        def jobs = context.selectCount().from(JOB).asField('jobs')
//                        def mines = context.selectCount().from(JOB_MINE).asField('mines')
//
//                        def result = context.select(schools, students, companies, jobs, mines).fetchOneMap()
//                        result.put('countries', 1)
//
//                        render json(result)
//                    }
//                }
//            }
//
//            path('mines') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<JobMine> mines = context.selectFrom(JOB_MINE)
//                                .fetch()
//                                .into(JobMine.class)
//                        render json(mines)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def name = params.get('name').textValue()
//
//                            assert name
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//
//                            JobMineRecord record = context
//                                    .insertInto(JOB_MINE)
//                                    .set(JOB_MINE.NAME, name)
//                                    .returning(JOB_MINE.JOB_MINE_ID)
//                                    .fetchOne()
//
//                            println "created job_mine with id: " + record.getValue(JOB_MINE.JOB_MINE_ID)
//                        }.then {
//                            response.send()
//                        }
//                    }
//                }
//            }
//
//            path('companies') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<Company> companies = context.selectFrom(COMPANY)
//                                .fetch()
//                                .into(Company.class)
//                        render json(companies)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def name = params.get('name')?.textValue()
//                            def description = params.get('description')?.textValue()
//                            def websiteUrl = params.get('websiteUrl')?.textValue()
//                            def industry = params.get('industry').textValue()
//
////                            def totalEmployees = params.get('totalEmployees')?.intValue()
////                            def foundedDate = params.get('foundedDate')?.textValue()
//
//                            def totalEmployees = 1
//                            def foundedDate = "2017"
//                            if (foundedDate) {
//                                foundedDate = new java.sql.Date(new SimpleDateFormat("yyyy").parse(foundedDate).getTime())
//                            }
//
//                            assert name
//                            assert description
//                            assert websiteUrl
//                            assert industry
//
//                            assert totalEmployees
//                            assert foundedDate
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//
//                            record = context
//                                    .insertInto(COMPANY)
//                                    .set(COMPANY.NAME, name)
//                                    .set(COMPANY.DESCRIPTION, description)
//                                    .set(COMPANY.WEBSITE_URL, websiteUrl)
//                                    .set(COMPANY.INDUSTRY, industry)
//                                    .set(COMPANY.TOTAL_EMPLOYEES, totalEmployees)
//                                    .set(COMPANY.FOUNDED_DATE, foundedDate)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(Company.class)
//                        }.then { Company company ->
//                            println "created company with id: " + company.getCompanyId()
//                            render json(company)
//                        }
//                    }
//                }
//            }
//
//            path('companies/:companyId') {
//                def companyId = pathTokens['companyId']
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        Company company = context.selectFrom(COMPANY)
//                                .where(COMPANY.COMPANY_ID.equal(companyId))
//                                .fetchOne()
//                                .into(Company.class)
//                        render json(company)
//                    }
//                }
//            }
//
//            path('companies/:companyId/jobs') {
//                def companyId = pathTokens['companyId']
//                byMethod {
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def title = params.get('title')?.textValue()
//                            def description = params.get('description')?.textValue()
//                            def type = params.get('type')?.textValue()
////                            def status = params.get('status')?.textValue()
//                            def totalOpenings = params.get('totalOpenings')?.intValue()
//                            def createdTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())
//
//                            assert title
//                            assert description
//                            assert type
////                            assert status
//                            assert totalOpenings >= 0
//                            assert createdTimestamp
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context.insertInto(JOB)
//                                    .set(JOB.TITLE, title)
////                                    .set(JOB.DESCRIPTION, description)
//                                    .set(JOB.TYPE, type)
//                                    .set(JOB.STATUS, 'Pending')
//                                    .set(JOB.TOTAL_OPENINGS, totalOpenings)
//                                    .set(JOB.CREATED_TIMESTAMP, createdTimestamp)
//                                    .set(JOB.COMPANY_ID, companyId)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(Job.class)
//                        }.then { Job createdJob ->
//                            println "created job with id: " + createdJob.getJobId()
//                            render json(createdJob)
//                        }
//                    }
//                }
//            }



//            path('jobs/:jobId') {
//                def jobId = pathTokens['jobId']
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        Job job = context.selectFrom(JOB)
//                                .where(JOB.JOB_ID.equal(jobId))
//                                .fetchOne()
//                                .into(Job.class)
//                        render json(job)
//                    }
//                }
//            }
//
//            path('jobs/applications') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<JobApp> jobApps = context.selectFrom(JOB_APP)
//                                .fetch()
//                                .into(JobApp.class)
//                        render json(jobApps)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def job_id = params.get('job_id').intValue()
//                            def job_app_package_id = params.get('job_app_package_id').intValue()
//
//                            assert job_id
//                            assert job_app_package_id
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context.insertInto(JOB_APP)
//                                    .set(JOB_APP.JOB_ID, job_id)
//                                    .set(JOB_APP.JOB_APP_PACKAGE_ID, job_app_package_id)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(JobApp.class)
//                        }.then { JobApp jobApp ->
//                            println "created job app with id: " + jobApp.getJobAppId()
//                            render json(jobApp)
//                        }
//                    }
//                }
//            }
//
//            path('jobs/interviews') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<JobInterview> jobInterviews = context.selectFrom(JOB_INTERVIEW)
//                                .fetch()
//                                .into(JobInterview.class)
//                        render json(jobInterviews)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def status = params.get('status').textValue()
//                            def created_timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())
//
////                            def job_id = params.get('job_id').intValue()
////                            def student_id = params.get('student_id').intValue()
////                            def location_id = params.get('location_id').intValue()
//
//                            assert status
//                            assert created_timestamp
//
////                            assert job_id
////                            assert student_id
////                            assert location_id
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context.insertInto(JOB_INTERVIEW)
//                                    .set(JOB_INTERVIEW.STATUS, status)
//                                    .set(JOB_INTERVIEW.CREATED_TIMESTAMP, created_timestamp)
////                                    .set(JOB_INTERVIEW.JOB_ID, job_id)
////                                    .set(JOB_INTERVIEW.STUDENT_ID, student_id)
////                                    .set(JOB_INTERVIEW.LOCATION_ID, location_id)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(JobInterview.class)
//                        }.then { JobInterview jobInterview ->
//                            println "created job interview with id: " + jobInterview.getJobInterviewId()
//                            render json(jobInterview)
//                        }
//                    }
//                }
//            }
//
//            path('jobs/offers') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<JobOffer> jobOffers = context.selectFrom(JOB_OFFER)
//                                .fetch()
//                                .into(JobOffer.class)
//                        render json(jobOffers)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def expiry_timestamp = new java.sql.Timestamp(
//                                    new SimpleDateFormat("yyyy-MM-dd").parse(
//                                            params.get('expiry_timestamp').textValue()).getTime())
//                            def salary = params.get('salary').intValue()
//
////                            def job_id = params.get('job_id').intValue()
////                            def student_id = params.get('student_id').intValue()
//
//                            assert expiry_timestamp
//                            assert salary
//
////                            assert job_id
////                            assert student_id
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context.insertInto(JOB_OFFER)
//                                    .set(JOB_OFFER.EXPIRY_TIMESTAMP, expiry_timestamp)
//                                    .set(JOB_OFFER.SALARY, salary)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(JobOffer.class)
//                        }.then { JobOffer jobOffer ->
//                            println "created job offer with id: " + jobOffer.getJobOfferId()
//                            render json(jobOffer)
//                        }
//                    }
//                }
//            }
//
//            path('schools') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<School> schools = context.selectFrom(SCHOOL)
//                                .fetch()
//                                .into(School.class)
//                        render json(schools)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def name = params.get('name')?.textValue()
//                            def description = params.get('description')?.textValue()
//                            def type = params.get('type')?.textValue()
//
//                            def totalStudents = params.get('totalStudents')?.intValue()
//
//                            def establishedDate = params.get('establishedDate')?.textValue()
//                            if (establishedDate) {
//                                establishedDate = new java.sql.Date(new SimpleDateFormat("yyyy").parse(establishedDate).getTime())
//                            }
//
//                            assert name
//                            assert description
//                            assert type
////                            assert establishedDate
////                            assert totalStudents
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context
//                                    .insertInto(SCHOOL)
//                                    .set(SCHOOL.NAME, name)
//                                    .set(SCHOOL.DESCRIPTION, description)
//                                    .set(SCHOOL.TYPE, type)
//                                    .set(SCHOOL.TOTAL_STUDENTS, (Integer) totalStudents)
//                                    .set(SCHOOL.ESTABLISHED_DATE, (java.sql.Date) establishedDate)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(School.class)
//                        }.then { School school ->
//                            println "created school with id: " + school.getSchoolId()
//                            render json(school)
//                        }
//                    }
//                }
//            }
//
//            path('schools/:schoolId') {
//                def schoolId = pathTokens['schoolId']
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        School school = context.selectFrom(SCHOOL)
//                                .where(SCHOOL.SCHOOL_ID.equal(schoolId))
//                                .fetchOne()
//                                .into(School.class)
//                        render json(school)
//                    }
//                }
//            }
//
//            path('students') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<Student> students = context.selectFrom(STUDENT)
//                                .fetch()
//                                .into(Student.class)
//                        render json(students)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            def username = params.get('username')?.textValue()
//                            def password = params.get('password')?.textValue()
//
//                            if(!username || !password) {
//                                clientError(400)
//                            }
//
//                            jobService.registerStudent(username, password)
//                        }.onError { Throwable e ->
//                            if(e.message.contains('unique constraint')) {
//                                clientError(409)
//                            }
//                            throw e
//                        }.then { Student student ->
//                            render json(student)
//                        }
//                    }
//                }
//            }
//
//            path('students/login') {
//                byMethod {
//                    post {
//                        parse(jsonNode()).map { params ->
//                            def username = params.get('username')?.textValue()
//                            def password = params.get('password')?.textValue()
//
//                            if (!username || !password) {
//                                clientError(400)
//                            }
//
//                            jobService.loginStudent(username, password)
//                        }.onError { Throwable e ->
//                            if (e instanceof InvalidCredentialsException) {
//                                clientError(401)
//                            }
//                        }.then { Student student ->
//                            render json(student)
//                        }
//                    }
//                }
//            }
//
//            path('students/:studentId') {
//                def studentId = pathTokens['studentId']
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        Student student = context.selectFrom(STUDENT)
//                                .where(STUDENT.STUDENT_ID.equal(studentId))
//                                .fetchOne()
//                                .into(Student.class)
//                        render json(student)
//                    }
//                }
//            }
//
//            path('students/packages') {
//                byMethod {
//                    get {
//                        DataSource dataSource = registry.get(DataSource.class)
//                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                        List<JobAppPackage> jobAppPackages = context.selectFrom(JOB_APP_PACKAGE)
//                                .fetch()
//                                .into(JobAppPackage.class)
//                        render json(jobAppPackages)
//                    }
//
//                    post {
//                        parse(jsonNode()).map { params ->
//                            log.info(params.toString())
//                            def name = params.get('name').textValue()
//
//                            assert name
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//                            context.insertInto(JOB_APP_PACKAGE)
//                                    .set(JOB_APP_PACKAGE.NAME, name)
//                                    .returning()
//                                    .fetchOne()
//                                    .into(JobAppPackage.class)
//                        }.then { JobAppPackage createdAppPackage ->
//                            println "created job app package with id: " + createdAppPackage.getJobAppPackageId()
//                            render json(createdAppPackage)
//                        }
//                    }
//                }
//            }
//
//            path('students/packages/documents') {
//                byMethod {
//                    get {
//                        render 'job app package documents'
//                    }
//
//                    post {
//                        parse(Form).then { Form form ->
//                            assert form.files().size()
//
//                            DataSource dataSource = registry.get(DataSource.class)
//                            DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)
//
//                            List<Document> documents = []
//
//                            form.files().each { String key, UploadedFile uploadedFile ->
//                                log.info(uploadedFile.getFileName())
//
//                                def name = uploadedFile.getFileName()
//                                byte[] file = uploadedFile.getBytes()
//
//                                assert name
//                                assert file
//
//                                Document document = context.insertInto(DOCUMENT)
//                                        .set(DOCUMENT.NAME, name)
//                                        .set(DOCUMENT.DATA, file)
//                                        .returning(DOCUMENT.DOCUMENT_ID, DOCUMENT.NAME)
//                                        .fetchOne()
//                                        .into(Document.class)
//
//                                documents.add(document)
//                            }
//
//                            render json(documents)
//                        }
//                    }
//                }
//            }
        }

        prefix('test') {
            prefix('data') {
                get('jobs') {
                    DataSource dataSource = registry.get(DataSource.class)
                    DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);

                    HttpClient httpClient = registry.get(HttpClient.class)

                    String uriStr = 'http://api.indeed.com/ads/apisearch' +
                            '?publisher=6453215428478291' +
                            '&v=2' +
                            '&format=json' +
                            '&limit=25' +
                            '&q=software' +
                            '&l=ab' +
                            '&co=ca'

                    httpClient.get(new URI(uriStr)).then {
                        def root = new JsonSlurper().parseText(it.body.text)

                        // get total results
                        int totalResults = root.totalResults

                        log.info(totalResults.toString())

                        uriStr += "&start=%s"

                        1.step(totalResults, 25) { step ->
                            uri = new URI(String.format(uriStr, step.toString()))


                            httpClient.get(uri).then { response ->
                                root = new JsonSlurper().parseText(response.body.text)
                                root.results.each {
//                                    if (!it.expired) {
                                        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm:ss z");

                                        assert it.jobtitle.toString()
                                        assert it.url.toString()
                                        assert it.company.toString()
                                        assert it.snippet.toString()
                                        assert it.date.toString()
                                        assert it.formattedLocation.toString()

                                        def title = it.jobtitle.toString()
                                        def company = it.company.toString()
                                        def companyUrl = it.url.toString()
                                        def description = it.snippet.toString()
                                        def location = it.formattedLocation.toString()

                                        log.info("Inserting: ${it.jobtitle.toString()} @ ${it.company.toString()}")
                                        create.insertInto(JOB)
                                                .set(JOB.TITLE, title)
                                                .set(JOB.COMPANY, company)
                                                .set(JOB.DESCRIPTION_9, description)
                                                .set(JOB.LOCATION, location)
//                                                .set(JOB.TYPE, 'Full-Time')
                                                .set(JOB.CREATED_TIMESTAMP, new java.sql.Date(formatter.parse(it.date.toString()).getTime()))
                                                .execute()
//                                    }
                                }
                            }
                        }
                        render totalResults.toString()
                    }
                }
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
            }
        }

        files {
            dir 'dist'
            indexFiles 'index.html'
        }
    }
}