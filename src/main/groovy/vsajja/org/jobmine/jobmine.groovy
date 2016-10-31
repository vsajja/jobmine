package vsajja.org.jobmine

import jooq.tables.pojos.*

/**
 * Created by vsajja on 2016-10-31.
 */
public class JobMine {
    public JobMine() {
        // TODO: student applies directly to a company?
        // TODO: student applies directly to multiple companies?
    }

    /**
     * Creates a student application for the given jobs.
     * @param student
     * @param jobs
     * @return
     */
    public apply(Student student, List<Job> jobs) {
        jobs.each { Job job ->
            apply(student, job)
        }
    }

    /**
     * Creates a student application for the given job.
     * @param student
     * @param job
     */
    public createApplication(Student student, Job job) {
        // When a student applies for a job on JobMine, the following should happen:
        // Job has a new student application.
        // Job status for student changes to Applied.
        // Job application count increases by 1.
        // Student application count increases by 1
        // Student available applications decreases by 1.
        // Notifications are sent out to the student (depending on settings).
        // Notifications are sent out to the employer (depending on settings).
        throw new UnsupportedOperationException()
    }

    /**
     * Cancels a student application for the given jobs.
     * @param student
     * @param job
     */
    public cancelApplication(Student student, List<Job> jobs) {
        jobs.each { Job job ->
            cancelApplication(job)
        }
    }

    /**
     * Cancels a student application for the given job.
     * @param student
     * @param job
     */
    public cancelApplication(Student student, Job job) {
        throw new UnsupportedOperationException()
    }
}
