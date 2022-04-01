DROP TABLE IF EXISTS movieland.genre;

CREATE TABLE IF NOT EXISTS movieland.genre
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT name UNIQUE (name)
);

ALTER TABLE movieland.genre OWNER to jacksparrow;

INSERT INTO movieland.genre (name) VALUES('драма');
INSERT INTO movieland.genre (name) VALUES('криминал');
INSERT INTO movieland.genre (name) VALUES('фэнтези');
INSERT INTO movieland.genre (name) VALUES('детектив');
INSERT INTO movieland.genre (name) VALUES('мелодрама');
INSERT INTO movieland.genre (name) VALUES('биография');
INSERT INTO movieland.genre (name) VALUES('комедия');
INSERT INTO movieland.genre (name) VALUES('фантастика');
INSERT INTO movieland.genre (name) VALUES('боевик');
INSERT INTO movieland.genre (name) VALUES('триллер');
INSERT INTO movieland.genre (name) VALUES('приключения');
INSERT INTO movieland.genre (name) VALUES('аниме');
INSERT INTO movieland.genre (name) VALUES('мультфильм');
INSERT INTO movieland.genre (name) VALUES('семейный');
INSERT INTO movieland.genre (name) VALUES('вестерн');
