package org.job

import groovy.json.JsonOutput
import io.netty.handler.codec.http.HttpResponseStatus
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.client.RequestSpec
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared

import static jooq.generated.Tables.*;

public class UserSpec extends JobmineSpec {
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
        testDatabase.deleteFrom(USER).where(USER.USERNAME.equal(TEST_STUDENT_USERNAME)).execute()
    }

    def "register user"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_STUDENT_USERNAME,
                     password: TEST_STUDENT_PASSWORD])
            )
        }

        when:
        post('register')

        then:
        response.statusCode == HttpResponseStatus.OK.code()
        userDao.count() == old(userDao.count()) + 1
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
        post('register')

        then:
        response.statusCode == HttpResponseStatus.CONFLICT.code()
        userDao.count() == old(userDao.count())
    }

//    def "register user with invalid params (bad request)"() {
//        setup:
//        requestSpec { RequestSpec request ->
//            request.body.type('application/json')
//            request.body.text(JsonOutput.toJson(
//                    [username: TEST_STUDENT_USERNAME,
//                     password: null])
//            )
//        }
//
//        when:
//        post('register')
//
//        then:
//        response.statusCode == HttpResponseStatus.BAD_REQUEST.code()
//        userDao.count() == old(userDao.count())
//    }
//
//    def "login user with invalid credentials (unauthorized)"() {
//        setup:
//        requestSpec { RequestSpec request ->
//            request.body.type('application/json')
//            request.body.text(JsonOutput.toJson(
//                    [username: TEST_STUDENT_USERNAME,
//                     password: 'wrong password'])
//            )
//        }
//
//        when:
//        post('login')
//
//        then:
//        response.statusCode == HttpResponseStatus.UNAUTHORIZED.code()
//    }
//
//    def "login user with invalid params (bad request)"() {
//        setup:
//        requestSpec { RequestSpec request ->
//            request.body.type('application/json')
//            request.body.text(JsonOutput.toJson(
//                    [username: TEST_STUDENT_USERNAME,
//                     password: null])
//            )
//        }
//
//        when:
//        post('login')
//
//        then:
//        response.statusCode == HttpResponseStatus.BAD_REQUEST.code()
//    }
//
//    def "login user"() {
//        setup:
//        requestSpec { RequestSpec request ->
//            request.body.type('application/json')
//            request.body.text(JsonOutput.toJson(
//                    [username: TEST_STUDENT_USERNAME,
//                     password: TEST_STUDENT_PASSWORD])
//            )
//        }
//
//        when:
//        post('login')
//
//        then:
//        response.statusCode == HttpResponseStatus.OK.code()
//    }
}
