package org.training.springboot.movieland.dao;

import java.util.List;

import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.model.Genre;

public interface GenreDao extends Dao<Genre, Long> {

	List<GenreDto> getMovieGenresById(Long movieId);

}
