package org.job

import com.google.inject.Inject
import jooq.generated.tables.daos.JobAppDao
import jooq.generated.tables.daos.JobShortlistDao
import jooq.generated.tables.pojos.Job
import jooq.generated.tables.pojos.JobApp
import jooq.generated.tables.pojos.JobShortlist
import jooq.generated.tables.pojos.User
import org.job.exceptions.InvalidCredentialsException
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.mindrot.jbcrypt.BCrypt
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.sql.DataSource
import static jooq.generated.Tables.*

class JobmineService {
    final Logger log = LoggerFactory.getLogger(this.class)

    DSLContext context
    JobShortlistDao jobShortlistDao
    JobAppDao jobAppDao

    @Inject
    JobmineService(DataSource dataSource) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES)
        jobShortlistDao = new JobShortlistDao(context.configuration())
        jobAppDao = new JobAppDao(context.configuration())
    }

    User register(String username, String password) {
        int BCRYPT_LOG_ROUNDS = 6
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))
        def user = context.insertInto(USER)
                .set(USER.USERNAME, username)
                .set(USER.PASSWORD, hashedPassword)
                .returning()
                .fetchOne()
                .into(User.class)
        return user
    }

    User login(String username, String password) throws InvalidCredentialsException {
        User user = getUser(username)

        if (user) {
            boolean passwordMatches = BCrypt.checkpw(password, user.getPassword())
            if (passwordMatches) {
                return user
            } else {
                throw new InvalidCredentialsException('Invalid username and password')
            }
        } else {
            throw new InvalidCredentialsException('Username not found')
        }
    }

    User getUser(String username) {
        User user = null
        def userData = context.selectFrom(USER)
                .where(USER.USERNAME.equal(username))
                .fetchOne()
        if (userData) {
            user = userData.into(User.class)
        }
        return user
    }

    List<User> getUsers() {
        List<User> users = context.selectFrom(USER)
                .fetch()
                .into(User.class)
        return users
    }

    List<JobShortlist> getJobShortlist(String userId) {
        List<JobShortlist> shortlist = context.selectFrom(JOB_SHORTLIST)
            .where(JOB_SHORTLIST.USER_ID.eq(Integer.valueOf(userId)))
            .fetch()
            .into(JobShortlist.class)

        return shortlist
    }

    def shortlist(String userId, String jobId) {
        List<JobShortlist> toToggle = context.selectFrom(JOB_SHORTLIST)
                .where(JOB_SHORTLIST.USER_ID.eq(userId))
                .and(JOB_SHORTLIST.JOB_ID.eq(jobId))
                .fetch()
                .into(JobShortlist.class)

        if (toToggle.size() == 0) {
            println 'INSERT IT'
            context.insertInto(JOB_SHORTLIST)
                    .set(JOB_SHORTLIST.USER_ID, Integer.valueOf(userId))
                    .set(JOB_SHORTLIST.JOB_ID, Integer.valueOf(jobId))
                    .execute()
        } else {
            println 'DELETE IT'
            jobShortlistDao.delete(toToggle)
        }
    }

    List<JobApp> getJobApps(String userId) {
        List<JobApp> jobApps = context.selectFrom(JOB_APP)
                .where(JOB_APP.USER_ID.eq(Integer.valueOf(userId)))
                .fetch()
                .into(JobApp.class)

        return jobApps
    }

    def apply(String userId, String jobId) {
        println userId
        println jobId

        List<JobApp> toToggle = context.selectFrom(JOB_APP)
                .where(JOB_APP.USER_ID.eq(userId))
                .and(JOB_APP.JOB_ID.eq(jobId))
                .fetch()
                .into(JobApp.class)

        if (toToggle.size() == 0) {
            context.insertInto(JOB_APP)
                    .set(JOB_APP.USER_ID, Integer.valueOf(userId))
                    .set(JOB_APP.JOB_ID, Integer.valueOf(jobId))
                    .execute()
        } else {
            jobAppDao.delete(toToggle)
        }
    }
}
