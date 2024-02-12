

CREATE TABLE user_tb(
                        user_id SERIAL PRIMARY KEY,
                        user_email VARCHAR(100) NOT NULL,
                        user_password VARCHAR(100) NOT NULL,
                        user_role VARCHAR(50) NOT NULL DEFAULT 'USER'
);

CREATE TABLE category_tb(
                            category_id SERIAL PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL,
                            category_date timestamp,
                            user_id INTEGER, CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_tb (user_id)
        on delete cascade on update cascade
);

CREATE TABLE task_tb(
                        task_id SERIAL PRIMARY KEY,
                        task_name VARCHAR(100) NOT NULL ,
                        task_description VARCHAR(255) NOT NULL,
                        task_date timestamp,
                        task_status VARCHAR(50) NOT NULL,
                        user_id INTEGER, CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_tb (user_id)
        on delete cascade on update cascade,
                        category_id INTEGER, CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category_tb (category_id)
        on delete cascade on update cascade
);