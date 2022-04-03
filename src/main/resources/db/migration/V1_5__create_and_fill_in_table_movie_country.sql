DROP TABLE IF EXISTS movieland.movie_country;

CREATE TABLE IF NOT EXISTS movieland.movie_country
(
    movie_id bigint NOT NULL,
    country_id bigint NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT movie_country_unique UNIQUE (movie_id, country_id)
);

ALTER TABLE movieland.movie_country OWNER to jacksparrow;

INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(1, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(2, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(3, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(4, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(5, 2);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(6, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(6, 3);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(7, 4);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(8, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(8, 5);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(9, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(10, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(11, 6);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(12, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(13, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(14, 6);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(15, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(15, 3);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(16, 3);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(16, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(17, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(17, 3);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(18, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(19, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(20, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(20, 5);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(21, 4);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(21, 7);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(21, 5);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(21, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(22, 4);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(23, 4);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(24, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(25, 1);
INSERT INTO movieland.movie_country (movie_id, country_id) VALUES(25, 3);
