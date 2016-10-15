import vsajja.org.redis.RedisConfig

import static ratpack.groovy.Groovy.ratpack

import vsajja.org.postgres.PostgresConfig
import vsajja.org.postgres.PostgresModule
import ratpack.config.ConfigData
import ratpack.config.ConfigDataBuilder
import ratpack.groovy.sql.SqlModule
import ratpack.hikari.HikariModule
import com.zaxxer.hikari.HikariConfig
import groovy.sql.Sql
import java.text.SimpleDateFormat

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
    }

    handlers {
        get {
            println "/ ${new SimpleDateFormat('yyyy-MM-dd HH:mm:ss.SSS').format(new Date())}"
            render "Hello world!"
        }

        get('ping') {
            println "/ping ${new SimpleDateFormat('yyyy-MM-dd HH:mm:ss.SSS').format(new Date())}"
            render "Hello world!"
        }

        get("db") { Sql sql ->
            def schemas = sql.rows("show schemas")
            render schemas?.join(', ')
        }

        files {
            dir "public"
        }
    }
}