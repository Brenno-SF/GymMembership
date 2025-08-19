CREATE TABLE plan_tb (
    plan_id UUID PRIMARY KEY,
    name_plan VARCHAR(255) NOT NULL,
    description_plan TEXT NOT NULL,
    max_capacity INT NOT NULL,
    duration_month INT NOT NULL
);

CREATE TABLE member_tb (
    member_id UUID PRIMARY KEY,
    name_member VARCHAR(255) NOT NULL,
    plan_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_member_plan FOREIGN KEY (plan_id) REFERENCES plan_tb(plan_id)
);

CREATE TABLE class_tb (
    class_id UUID PRIMARY KEY,
    name_class VARCHAR(255) NOT NULL,
    description_class TEXT NOT NULL,
    date_hour TIMESTAMP NOT NULL
);
CREATE TABLE presence_tb (
    presence_id UUID PRIMARY KEY,
    member_id_fk UUID NOT NULL,
    class_id_fk UUID NOT NULL,
    presence BOOLEAN NOT NULL,
    register DATE NOT NULL,
    CONSTRAINT fk_member_presence FOREIGN KEY (member_id_fk) REFERENCES member_tb(member_id),
    CONSTRAINT fk_class_presence FOREIGN KEY (class_id_fk) REFERENCES class_tb(class_id)
);
