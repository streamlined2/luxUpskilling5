DROP TABLE IF EXISTS movieland.country;

CREATE TABLE IF NOT EXISTS movieland.country
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id),
    CONSTRAINT country_unique_name UNIQUE (name)
);

ALTER TABLE movieland.country OWNER to jacksparrow;

INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(1, 'США');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(2, 'Франция');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(3, 'Великобритания');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(4, 'Италия');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(5, 'Германия');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(6, 'Япония');
INSERT INTO movieland.country (id, name) OVERRIDING SYSTEM VALUE VALUES(7, 'Испания');
