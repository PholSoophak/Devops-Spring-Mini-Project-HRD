CREATE TABLE user_tb(
                        user_id SERIAL NOT NULL PRIMARY KEY,
                        user_email VARCHAR(100) NOT NULL unique ,
                        user_password VARCHAR(200) NOT NULL,
                        user_role VARCHAR  default 'USER' NOT NULL
);

drop  table user_tb;

CREATE TABLE category_tb(
                            category_id SERIAL PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL,
                            category_date timestamp default now(),
                            user_id INTEGER, CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_tb (user_id)
        on delete cascade on update cascade
);

drop table category_tb;