package org.jobmine

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by vsajja on 2016-11-02.
 */
public class JobmineSpec extends Specification {

    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest sut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient httpClient = sut.httpClient

    def "hello world"() {
        when:
        get()

        then:
        response.body.text == 'hello world!'
    }

    def "get stats"() {
        expect: false
    }

    def "search jobs"() {
        when:
        get('api/v1/jobs')

        then:
        response.body.text
    }

    def "search jobs by keyword"() {
        expect: false
    }

    def "search jobs by keyword and location"() {
        expect: false
    }

    def "get schools"() {
        when:
        get('api/v1/schools')

        then:
        response.body.text
    }

    def "get students"() {
        when:
        get('api/v1/students')

        then:
        response.body.text
    }

    def "get companies"() {
        when:
        get('api/v1/companies')

        then:
        response.body.text
    }

    def "get countries"() {
        when:
        get('api/v1/countries')

        then:
        response.body.text
    }

    def "approve pending jobs"() {
        expect: false
    }

    def "send notifications"() {
        expect: false
    }
}
