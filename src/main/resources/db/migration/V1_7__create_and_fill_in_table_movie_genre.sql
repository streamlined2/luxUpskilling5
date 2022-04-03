DROP TABLE IF EXISTS movieland.movie_genre;

CREATE TABLE IF NOT EXISTS movieland.movie_genre
(
    movie_id bigint NOT NULL,
    genre_id bigint NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id),
    CONSTRAINT movie_genre_unique UNIQUE (movie_id, genre_id)
);

ALTER TABLE movieland.movie_genre OWNER to jacksparrow;

INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(1, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(1, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(2, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(2, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(2, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(2, 4);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(3, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(3, 5);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(4, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(4, 6);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(5, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(5, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(5, 6);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(6, 8);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(6, 9);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(6, 10);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(6, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(6, 4);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(7, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(7, 5);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(7, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(8, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(8, 10);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(8, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(9, 8);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(9, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(9, 9);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(9, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(10, 8);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(10, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(10, 9);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(10, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(11, 12);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(11, 13);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(11, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(11, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(11, 14);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(12, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(12, 5);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(13, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(14, 12);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(14, 13);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(14, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(14, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(15, 9);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(15, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(16, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(16, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(17, 8);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(17, 9);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(17, 10);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(17, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(17, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(18, 13);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(18, 3);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(18, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(18, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(18, 14);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(19, 10);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(19, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(19, 4);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(19, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(20, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(21, 15);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(22, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(23, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(23, 2);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(24, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(24, 15);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(24, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(24, 7);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(25, 1);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(25, 11);
INSERT INTO movieland.movie_genre (movie_id, genre_id) VALUES(25, 15);
