package org.job

import com.google.inject.Inject
import jooq.generated.tables.daos.JobShortlistDao
import jooq.generated.tables.daos.StudentDao
import jooq.generated.tables.pojos.JobShortlist
import jooq.generated.tables.pojos.Student
import org.job.exceptions.InvalidCredentialsException
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.mindrot.jbcrypt.BCrypt
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.sql.DataSource
import static jooq.generated.Tables.*;

class JobService {
    final Logger log = LoggerFactory.getLogger(this.class)

    DSLContext context
    StudentDao studentDao
    JobShortlistDao jobShortlistDao

    @Inject
    public JobService(DataSource dataSource) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES)
        studentDao = new StudentDao(context.configuration())
        jobShortlistDao = new JobShortlistDao(context.configuration())
    }

    public Student getStudent(String username) {
        Student student = null
        def studentData = context.selectFrom(STUDENT)
                .where(STUDENT.USERNAME.equal(username))
                .fetchOne()
        if (studentData) {
            student = studentData.into(Student.class)
        }
        return student
    }

    public Student registerStudent(String username, String password) {
        log.info("registerStudent: $username")

        int BCRYPT_LOG_ROUNDS = 6
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))

        def student = context.insertInto(STUDENT)
                .set(STUDENT.USERNAME, username)
                .set(STUDENT.PASSWORD, hashedPassword)
                .returning()
                .fetchOne()
                .into(Student.class)
        return student
    }

    public Student loginStudent(String username, String password) throws InvalidCredentialsException {
        Student student = getStudent(username)

        if(student) {
            boolean passwordMatches = BCrypt.checkpw(password, student.getPassword())
            if (passwordMatches) {
                return student
            } else {
                throw new InvalidCredentialsException('Invalid username and password')
            }
        } else {
            throw new InvalidCredentialsException('Username not found')
        }
    }

    public shortlistJob(String username, String jobId) {
        Student student = getStudent(username)

        if(student) {
//            JobShortlist shortlist = new JobShortlist()
//            shortlist.studentId = student.studentId
//            shortlist.jobId = jobId
//            jobShortlistDao.insert(shortlist)
            println 'shortlisting'
        }
    }
}
