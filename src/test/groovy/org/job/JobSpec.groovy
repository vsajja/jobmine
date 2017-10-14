package org.job

import jooq.generated.tables.daos.StudentDao
import org.job.postgres.PostgresConfig
import org.job.postgres.PostgresModule
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource

class JobSpec extends Specification {
    @Shared
    DSLContext testDatabase

    @Shared
    StudentDao studentDao

    def setupSpec() {
        Properties props = new Properties()
        props.load(new FileInputStream('src/ratpack/db.properties'))

        PostgresConfig postgresConfig = new PostgresConfig()
        postgresConfig.user = props.get('postgres.user')
        postgresConfig.password = props.get('postgres.password')
        postgresConfig.serverName = props.get('postgres.serverName')
        postgresConfig.databaseName = props.get('postgres.databaseName')

        DataSource dataSource = new PostgresModule().dataSource(postgresConfig)
        testDatabase = DSL.using(dataSource, SQLDialect.POSTGRES);
        studentDao = new StudentDao(testDatabase.configuration())
    }
}
