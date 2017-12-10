package org.job

import com.google.inject.Inject
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

class JobService {
    final Logger log = LoggerFactory.getLogger(this.class)

    DSLContext context

    @Inject
    JobService(DataSource dataSource) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES)
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

        if(user) {
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
        User student = null
        def userData = context.selectFrom(USER)
                .where(USER.USERNAME.equal(username))
                .fetchOne()
        if (userData) {
            student = userData.into(User.class)
        }
        return student
    }

    List<User> getUsers() {
        List<User> users = context.selectFrom(USER)
                .fetch()
                .into(User.class)
        return users
    }
}
