DROP TABLE IF EXISTS movieland."user";

CREATE TABLE IF NOT EXISTS movieland."user"
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    login character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    role character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT unique_login UNIQUE (login),    
    CONSTRAINT unique_name UNIQUE (name)
);

ALTER TABLE movieland."user" OWNER to jacksparrow;

INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(1, 'Рональд Рейнольдс', 'ronald.reynolds66@example.com', 'paco', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(2, 'Дарлин Эдвардс', 'darlene.edwards15@example.com', 'bricks', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(3, 'Габриэль Джексон', 'gabriel.jackson91@example.com', 'hjkl', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(4, 'Дэрил Брайант', 'daryl.bryant94@example.com', 'exodus', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(5, 'Нил Паркер', 'neil.parker43@example.com', '878787', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(6, 'Трэвис Райт', 'travis.wright36@example.com', 'smart', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(7, 'Амелия Кэннеди', 'amelia.kennedy58@example.com', 'beaker', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(8, 'Айда Дэвис', 'ida.davis80@example.com', 'pepsi1', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(9, 'Джесси Паттерсон', 'jessie.patterson68@example.com', 'tommy', 'USER');
INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(10, 'Деннис Крейг', 'dennis.craig82@example.com', 'coldbeer', 'USER');

INSERT INTO movieland."user" (id, name, login, password, role) OVERRIDING SYSTEM VALUE VALUES(999, 'Judy Blacksmith', 'superuser@luxoft.com', 'secret', 'ADMIN');
