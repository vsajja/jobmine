import jooq.tables.JobPosting
import jooq.tables.daos.JobPostingDao
import org.jooq.Configuration
import org.jooq.SQLDialect
import org.jooq.impl.DefaultConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import vsajja.org.redis.RedisConfig
import javax.sql.DataSource

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json;

import vsajja.org.postgres.PostgresConfig
import vsajja.org.postgres.PostgresModule
import ratpack.config.ConfigData
import ratpack.config.ConfigDataBuilder
import ratpack.groovy.sql.SqlModule
import ratpack.hikari.HikariModule
import ratpack.handling.RequestLogger

import com.zaxxer.hikari.HikariConfig
import java.text.SimpleDateFormat

final Logger log = LoggerFactory.getLogger(this.class)

ratpack {
    bindings {
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

        bindInstance PostgresConfig, configData.get('/postgres', PostgresConfig)
        bindInstance RedisConfig, configData.get('/redis', RedisConfig)

        module HikariModule, { HikariConfig config ->
            config.dataSource =
                    new PostgresModule().dataSource(
                            configData.get('/postgres', PostgresConfig))
        }
        module SqlModule

        bind JobPostingDao
    }

    handlers {
        all RequestLogger.ncsa(log)

        get {
            println "/ ${new SimpleDateFormat('yyyy-MM-dd HH:mm:ss.SSS').format(new Date())}"
            render 'Hello world!'
        }

        get('ping') {
            println "/ping ${new SimpleDateFormat('yyyy-MM-dd HH:mm:ss.SSS').format(new Date())}"
            render 'Hello world!'
        }

        get('jobs') {
//            DataSource dataSource = registry.get(DataSource.class);
//            DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES)
//            SelectJoinStep from = create.select().from(JobPosting.JOB_POSTING)
//            Blocking.get {
//                from.fetch().intoMaps()
//            }.then { maps ->
//                render maps
//            }

            response.headers.add('Access-Control-Allow-Origin', '*');

            DataSource dataSource = registry.get(DataSource.class);
            Configuration configuration = new DefaultConfiguration().set(dataSource).set(SQLDialect.POSTGRES)
            List<JobPosting> jobPostings = new JobPostingDao(configuration).findAll()
            render json(jobPostings)
        }

        files {
            dir 'dist'
        }
    }
}