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
    @Shared
    JobMineDao jobMineDao

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
        Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)

        jobMineDao = new JobMineDao(configuration)
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    def cleanupSpec() {

    }

    def "jobmine system creates school (UW)"() {
        expect: false
    }

    def "jobmine system creates student (vsajja)"() {
        expect: false
    }

    def "jobmine system creates a company (jobmine)"() {
        expect: false
    }

    def "UW creates a mine (UW's jobmine)"() {
        expect: false
    }

    def "jobmine posts a job (jobmine's Founder) on UW's jobmine"() {
        expect: false
    }

    def "vsajja searches for jobs on UW's jobmine"() {
        expect: false
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
