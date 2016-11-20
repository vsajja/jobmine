CREATE TABLE company
(
    company_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    description VARCHAR,
    website_url VARCHAR,
    total_employees INTEGER,
    industry VARCHAR,
    founded_date DATE,
    location_id INTEGER,
    image_id INTEGER
);
CREATE TABLE document
(
    document_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    data BYTEA,
    job_app_package_id INTEGER
);
CREATE TABLE image
(
    image_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    src VARCHAR,
    url VARCHAR,
    data BYTEA
);
CREATE TABLE job
(
    job_id BIGINT PRIMARY KEY NOT NULL,
    title VARCHAR,
    description VARCHAR,
    created_timestamp TIMESTAMP,
    type VARCHAR,
    status VARCHAR,
    total_openings INTEGER,
    job_mine_id INTEGER,
    company_id INTEGER,
    location_id INTEGER
);
CREATE TABLE job_app
(
    job_app_id BIGINT PRIMARY KEY NOT NULL,
    job_id INTEGER,
    job_app_package_id INTEGER
);
CREATE TABLE job_app_package
(
    job_app_package_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    student_id INTEGER
);
CREATE TABLE job_interview
(
    job_interview_id BIGINT PRIMARY KEY NOT NULL,
    status VARCHAR,
    created_timestamp TIMESTAMP,
    job_id INTEGER,
    student_id INTEGER,
    location_id INTEGER
);
CREATE TABLE job_mine
(
    job_mine_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    school_id INTEGER
);
CREATE TABLE job_offer
(
    job_offer_id BIGINT PRIMARY KEY NOT NULL,
    expiry_timestamp TIMESTAMP,
    salary VARCHAR,
    job_id INTEGER,
    student_id INTEGER
);
CREATE TABLE location
(
    location_id BIGINT PRIMARY KEY NOT NULL,
    city VARCHAR,
    state_or_province VARCHAR,
    zip_or_postalcode VARCHAR,
    country VARCHAR
);
CREATE TABLE school
(
    school_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR,
    type VARCHAR,
    total_students INTEGER,
    established_date DATE,
    description VARCHAR,
    location_id INTEGER,
    image_id INTEGER
);
CREATE TABLE student
(
    student_id BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR,
    last_name VARCHAR,
    username VARCHAR,
    email_address VARCHAR,
    last_loggedin_timestamp TIMESTAMP,
    employment_status VARCHAR,
    joined_timestamp TIMESTAMP,
    karma INTEGER,
    total_views INTEGER,
    age INTEGER,
    gender VARCHAR,
    salary VARCHAR,
    relationship_status VARCHAR,
    dreams VARCHAR,
    phone_number VARCHAR,
    employment_history VARCHAR,
    skills VARCHAR,
    school_id INTEGER,
    location_id INTEGER,
    image_id INTEGER
);
ALTER TABLE company ADD FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE company ADD FOREIGN KEY (image_id) REFERENCES image (image_id);
ALTER TABLE document ADD FOREIGN KEY (job_app_package_id) REFERENCES job_app_package (job_app_package_id);
ALTER TABLE job ADD FOREIGN KEY (job_mine_id) REFERENCES job_mine (job_mine_id);
ALTER TABLE job ADD FOREIGN KEY (company_id) REFERENCES company (company_id);
ALTER TABLE job ADD FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE job_app ADD FOREIGN KEY (job_id) REFERENCES job (job_id);
ALTER TABLE job_app ADD FOREIGN KEY (job_app_package_id) REFERENCES job_app_package (job_app_package_id);
ALTER TABLE job_app_package ADD FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE job_interview ADD FOREIGN KEY (job_id) REFERENCES job (job_id);
ALTER TABLE job_interview ADD FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE job_interview ADD FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE job_mine ADD FOREIGN KEY (school_id) REFERENCES school (school_id);
ALTER TABLE job_offer ADD FOREIGN KEY (job_id) REFERENCES job (job_id);
ALTER TABLE job_offer ADD FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE school ADD FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE school ADD FOREIGN KEY (image_id) REFERENCES image (image_id);
ALTER TABLE student ADD FOREIGN KEY (school_id) REFERENCES school (school_id);
ALTER TABLE student ADD FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE student ADD FOREIGN KEY (image_id) REFERENCES image (image_id);