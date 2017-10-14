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

    static final String TEST_USER_VSAJJA_USERNAME = 'UserSpec_test_user_vsajja'
    static final String TEST_USER_VSAJJA_PASSWORD = 'UserSpec_test_user_vsajja_password'

    def setupSpec() {
    }

    def cleanupSpec() {
        testDatabase.deleteFrom(STUDENT).where(STUDENT.USERNAME.equal(TEST_USER_VSAJJA_USERNAME)).execute()
    }

    def "register student"() {
        setup:
        requestSpec { RequestSpec request ->
            request.body.type('application/json')
            request.body.text(JsonOutput.toJson(
                    [username: TEST_USER_VSAJJA_USERNAME,
                     password: TEST_USER_VSAJJA_PASSWORD])
            )
        }
        when:
        post('api/v1/students')

        then:
        response.statusCode == HttpResponseStatus.OK.code()
        studentDao.count() == old(studentDao.count()) + 1
    }

//    def "create student"() {
//        setup:
//        def first_name = this.class.getSimpleName()
//        def last_name = this.class.getSimpleName()
//        def username = this.class.getSimpleName()
//        def email_address = "${first_name}@waterloo.ca"
//        def employment_status = 'Unemployed'
//        def karma = 1
//        def total_views = 1
//        def age = 19
//        def gender = "Male"
//        def salary = 1
//        def relationship_status = "Single"
//        def dreams = "cure cancer"
//        def phone_number = "(519) 502-7991"
//        def employment_history = "No employment history"
//        def skills = "Really good at Photoshop"
//
//
//        requestSpec { RequestSpec request ->
//            request.body.type('application/json')
//            request.body.text(JsonOutput.toJson(
//                    [first_name         : first_name,
//                     last_name          : last_name,
//                     username           : username,
//                     email_address      : email_address,
//                     employment_status  : employment_status,
//                     karama             : karma,
//                     total_views        : total_views,
//                     age                : age,
//                     gender             : gender,
//                     salary             : salary,
//                     relationship_status: relationship_status,
//                     dreams             : dreams,
//                     phone_number       : phone_number,
//                     employment_history : employment_history,
//                     skills             : skills])
//            )
//        }
//
//        when:
//        post('api/v1/students')
//
//        then:
//        response.statusCode == 200
//    }
}
