DROP TABLE IF EXISTS movieland.genre;

CREATE TABLE IF NOT EXISTS movieland.genre
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT name UNIQUE (name)
);

ALTER TABLE movieland.genre OWNER to jacksparrow;

INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(1, 'драма');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(2, 'криминал');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(3, 'фэнтези');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(4, 'детектив');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(5, 'мелодрама');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(6, 'биография');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(7, 'комедия');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(8, 'фантастика');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(9, 'боевик');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(10, 'триллер');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(11, 'приключения');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(12, 'аниме');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(13, 'мультфильм');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(14, 'семейный');
INSERT INTO movieland.genre (id, name) OVERRIDING SYSTEM VALUE VALUES(15, 'вестерн');
