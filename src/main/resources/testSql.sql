CREATE TABLE student (
  id UUID NOT NULL,
   name VARCHAR(255),
   age INTEGER,
   school_name VARCHAR(255),
   phone_number INTEGER,
   CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE project (
  id UUID NOT NULL,
   project_name VARCHAR(255),
   start TIMESTAMP WITHOUT TIME ZONE,
   "end" VARCHAR(255),
   students_id UUID,
   CONSTRAINT pk_project PRIMARY KEY (id)
);

ALTER TABLE project ADD CONSTRAINT FK_PROJECT_ON_STUDENTS FOREIGN KEY (students_id) REFERENCES student (id);

CREATE TABLE task (
  id UUID NOT NULL,
   task_name VARCHAR(255),
   number INTEGER,
   project_id UUID,
   CONSTRAINT pk_task PRIMARY KEY (id)
);

ALTER TABLE task ADD CONSTRAINT FK_TASK_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);