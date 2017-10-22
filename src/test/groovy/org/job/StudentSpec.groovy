package org.job

import groovy.json.JsonOutput
import io.netty.handler.codec.http.HttpResponseStatus
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.client.RequestSpec
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared

import static jooq.generated.Tables.*;

public class StudentSpec extends JobSpec {
    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest sut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient httpClient = sut.httpClient

    static final String TEST_STUDENT_USERNAME = 'UserSpec_test_user_vsajja'
    static final String TEST_STUDENT_PASSWORD = 'UserSpec_test_user_vsajja_password'

    def setupSpec() {
    }

    def cleanupSpec() {
        testDatabase.deleteFrom(STUDENT).where(STUDENT.USERNAME.equal(TEST_STUDENT_USERNAME)).execute()
    }

    def "register student"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: TEST_STUDENT_PASSWORD])
            )
        }

        when:
        post('api/v1/students')

        then:
        response.statusCode == HttpResponseStatus.OK.code()
        studentDao.count() == old(studentDao.count()) + 1
    }

    def "register student again (conflict)"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: TEST_STUDENT_PASSWORD])
            )
        }

        when:
        post('api/v1/students')

        then:
        response.statusCode == HttpResponseStatus.CONFLICT.code()
        studentDao.count() == old(studentDao.count())
    }

    def "register student with invalid params (bad request)"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: null])
            )
        }

        when:
        post('api/v1/students')

        then:
        response.statusCode == HttpResponseStatus.BAD_REQUEST.code()
        studentDao.count() == old(studentDao.count())
    }

    def "login student with invalid credentials (unauthorized)"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: 'wrong password'])
            )
        }

        when:
        post('api/v1/students/login')

        then:
        response.statusCode == HttpResponseStatus.UNAUTHORIZED.code()
    }

    def "login student with invalid params (bad request)"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: null])
            )
        }

        when:
        post('api/v1/students/login')

        then:
        response.statusCode == HttpResponseStatus.BAD_REQUEST.code()
    }

    def "login student"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: TEST_STUDENT_PASSWORD])
            )
        }

        when:
        post('api/v1/students/login')

        then:
        response.statusCode == HttpResponseStatus.OK.code()
    }

    def "get students"() {
        when:
        get('api/v1/students')

        then:
        response.statusCode == HttpResponseStatus.OK.code()
    }

    /**
     * A student or alumni logs into the job app and lands at the home page.
     *
     * Home page:
     * - search for jobs
     * - job shortlist
     * - interviews
     * - offers
     *
     *
     * Profile page:
     *
     */
}
