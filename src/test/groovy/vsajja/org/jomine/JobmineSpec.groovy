package vsajja.org.jomine

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by vsajja on 2016-11-02.
 */
public class JobmineSpec extends Specification {

    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest sut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient httpClient = sut.httpClient

    def "hello world endpoint"() {
        when:
        get()

        then:
        response.body.text == 'hello world!'
    }

    def "get stats"()
    {
        expect: false
    }

    def "search jobs by keyword"() {
        expect: false
    }

    def "search jobs by keyword and location"() {
        expect: false
    }

    def "get schools"() {
        expect: false
    }

    def "get students"() {
        expect: false
    }

    def "get companies"() {
        expect: false
    }

    def "get countries"() {
        expect: false
    }

    def "approve pending jobs"()
    {
        expect: false
    }

    def "send notifications"()
    {
        expect: false
    }
}
