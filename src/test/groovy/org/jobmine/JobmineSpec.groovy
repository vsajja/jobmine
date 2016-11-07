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

import javax.sql.DataSource

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

    def cleanupSpec()
    {

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

    def "create a job mine"() {
        setup:
        def name = this.class.getName()

        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson([name: name ]))
        }

        when:
        post('api/v1/jobmine')

        then:
        response.statusCode == 200
    }

    def "get all jobs in a job mine"() {
        expect: false
    }
}
