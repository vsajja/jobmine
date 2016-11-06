package org.jobmine

import jooq.generated.tables.daos.JobMineDao
import jooq.generated.tables.pojos.JobMine
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
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource
import static jooq.generated.Tables.JOB

/**
 * Created by vsajja on 2016-11-02.
 */
public class JobmineSpec extends Specification {
    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest sut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient httpClient = sut.httpClient

    @Shared
    DSLContext context
    @Shared
    JobMineDao jobMineDao

    def setup() {
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

    def "hello"() {
        when:
        get('api/v1/jobmine')

        then:
        with(response) {
            statusCode == 200
            body.text == 'hello pleb'
        }
    }

    def "create job_mine"() {
        when:
//        JobMine mine = new JobMine(1, "Waterloo's JobMine")
//        jobMineDao.insert(mine)

        post('api/v1/jobmine')

        then:
        response.statusCode == 200
    }

//    def "get schools"() {
//        when:
//        get('api/v1/jobmine/school')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "get companies"() {
//        when:
//        get('api/v1/jobmine/company')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "add school"() {
//        when:
//        post('api/v1/jobmine/school')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "add company"() {
//        when:
//        post('api/v1/jobmine/company')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "search for jobs"() {
//        when:
//        get('api/v1/jobmine/job')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "search for jobs by keyword"() {
//        when:
//        get('api/v1/jobmine/job')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "search for jobs by keyword and location"() {
//        when:
//        get('api/v1/jobmine/job')
//
//        then:
//        response.statusCode == 200
//    }
//
//    def "approve pending jobs"() {
//        when:
//        post('api/v1/jobmine/job/approve')
//
//        then:
//        response.statusCode == 200
//    }
}
