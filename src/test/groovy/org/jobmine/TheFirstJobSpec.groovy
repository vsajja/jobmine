package org.jobmine

import groovy.json.JsonOutput
import jooq.generated.tables.daos.JobMineDao
import org.jobmine.postgres.PostgresConfig
import org.jobmine.postgres.PostgresModule
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import ratpack.config.ConfigData
import ratpack.config.ConfigDataBuilder
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.client.RequestSpec
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import javax.sql.DataSource

/**
 * Created by vsajja on 2016-11-02.
 */
@Stepwise
public class TheFirstJobSpec extends Specification {
    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest sut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient httpClient = sut.httpClient

    @Shared
    DSLContext context

    def setupSpec() {
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
        DataSource dataSource = new PostgresModule().dataSource(configData.get('/postgres', PostgresConfig))
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    def cleanupSpec() {

    }

    def "1. create school (UW)"() {
        setup:
        def name = 'University of Waterloo'
        def type = 'University'
        def total_students = 35900
        def established_date = '1986'
        def description = "The University of Waterloo is a public research university with a main campus located in Waterloo, Ontario. The main campus is located on 404 hectares of land in 'Uptown' Waterloo, adjacent to Waterloo Park"

        def image_id = null
        def location_id = null

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [name            : name,
                     type            : type,
                     total_students  : total_students,
                     established_date: established_date,
                     description     : description])
            )
        }

        when:
        post('api/v1/schools')

        then:
        response.statusCode == 200
        println response.body.text
    }

    def "2. create job_mine (UW's jobmine)"() {
        setup:
        def name = 'University of Waterloo jobmine'

        def school_id = null

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson([name: name]))
        }

        when:
        post('api/v1/mines')

        then:
        response.statusCode == 200
    }

    def "3. create student (vsajja)"() {
        setup:
        def first_name = 'Vinod'
        def last_name = 'Sajja'
        def username = 'vsajja'
        def email_address = 'vsajja@engmail.uwaterloo.ca'
        def employment_status = 'Unemployed'
        def karma = 1
        def total_views = 1
        def age = 19
        def gender = 'Male'
        def salary = 1
        def relationship_status = 'Single'
        def dreams = 'help people find jobs'
        def phone_number = '(519) 502-7991'
        def employment_history = 'No history'
        def skills = 'fantasy baseball guru'

        def image_id = null
        def location_id = null
        def school_id = null

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [first_name         : first_name,
                     last_name          : last_name,
                     username           : username,
                     email_address      : email_address,
                     employment_status  : employment_status,
                     karma              : karma,
                     total_views        : total_views,
                     age                : age,
                     gender             : gender,
                     salary             : salary,
                     relationship_status: relationship_status,
                     dreams             : dreams,
                     phone_number       : phone_number,
                     employment_history : employment_history,
                     skills             : skills])
            )
        }

        when:
        post('api/v1/students')

        then:
        response.statusCode == 200
    }

    def "4. create company (jobmine)"() {
        setup:
        def name = 'jobmine'
        def description = 'jobmine is a website that helps people find jobs.'
        def website_url = 'www.jobmine.ca'
        def total_employees = 1
        def industry = 'Software'
        def founded_date = '2017-01-01'

        def image_id = null
        def location_id = null

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [name           : name,
                     description    : description,
                     website_url    : website_url,
                     total_employees: total_employees,
                     industry       : industry,
                     founded_date   : founded_date])
            )
        }

        when:
        post('api/v1/companies')

        then:
        response.statusCode == 200
    }

    def "5. post job on UW's jobmine (Founder)"() {
        setup:
        def title = 'Founder'
        def description = 'Find jobmine.ca'
        def type = 'Full-Time'
        def status = 'Approved'
        def total_openings = 1

        def location_id = null
        def job_mine_id = null
        def company_id = null

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [title         : title,
                     description   : description,
                     type          : type,
                     status        : status,
                     total_openings: total_openings])
            )
        }

        when:
        post('api/v1/jobs')

        then:
        response.statusCode == 200
    }

    def "6. searches for jobs on UW's jobmine"() {
        when:
        get('api/v1/jobs')

        then:
        response.statusCode == 200
    }

    def "vsajja shortlists for jobs on UW's jobmine"() {
        expect: false
    }

    def "vsajja creates a job_app_package (vsajja's application)"() {
        expect: false
    }

    def "vsajja uploads a document (cover letter) to vsajja's application"() {
        expect: false
    }

    def "vsajja uploads a document (resume) to vsajja's application"() {
        expect: false
    }

    def "vsajja views job shortlist"() {
        expect: false
    }

    def "vsajja applies to Jobmine's Founder on UW's jobmine with vsajja's applicaiton"() {
        expect: false
    }

    def "jobmine selects vsajja for interview"() {
        expect: false
    }

    def "vsajja and jobmine schedule interview"() {
        expect: false
    }

    def "jobmine conducts interview and sends an offer to vsajja to be jobmine's founder"() {
        expect: false
    }

    def "vsajja accepts offer and becomes jobmine's founder"() {
        expect: false
    }
}
