import com.zaxxer.hikari.HikariConfig
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import jooq.generated.tables.pojos.Job
import jooq.generated.tables.pojos.User
import org.job.JobService
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

        path('login') {
            byMethod {
                post {
                    parse(jsonNode()).map { params ->
                        def username = params.get('username')?.textValue()
                        def password = params.get('password')?.textValue()

                        assert username
                        assert password

                        jobService.login(username, password)
                    }.onError { Throwable e ->
                        if (e instanceof IllegalArgumentException) {
                            clientError(401)
                        }
                    }.then { User user ->
                        if (user) {
                            render json(user)
                        } else {
                            clientError(404)
                        }
                    }
                }
            }
        }

        post('register') {
            parse(jsonNode()).map { params ->
                def username = params.get('username')?.textValue()
                def password = params.get('password')?.textValue()

                jobService.register(username, password)
            }.onError { Throwable e ->
                if (e.message.contains('unique constraint')) {
                    clientError(409)
                }
                throw e
            }.then { User user ->
                render json(user)
            }
        }

        prefix('api/v1') {
            all {
                response.headers.add('Access-Control-Allow-Origin', '*')
                response.headers.add('Access-Control-Allow-Headers', 'origin, x-requested-with, content-type, Authorization')
                response.headers.add('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS')
                next()
            }

            path('jobs') {
                byMethod {
                    get {
                        String query = request.queryParams.q?.toLowerCase()
                        String location = request.queryParams.l?.toLowerCase()

                        DataSource dataSource = registry.get(DataSource.class)
                        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES)

                        def jobsQ = context.selectFrom(JOB)
                        if (query) {
                            jobsQ.where(DSL.lower(JOB.TITLE).like("%$query%"))
                            jobsQ.or(DSL.lower(JOB.COMPANY).like("%$query%"))
                        }
                        if (location) {
                            jobsQ.where(DSL.lower(JOB.LOCATION).like("%$location%"))
                        }

                        jobsQ.orderBy(JOB.CREATED_TIMESTAMP.desc())

                        List<Job> jobs = jobsQ.fetch().into(Job.class)
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
        }

        files {
            dir 'dist'
            indexFiles 'index.html'
        }
    }
}
