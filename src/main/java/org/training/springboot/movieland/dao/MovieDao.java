package org.training.springboot.movieland.dao;

import java.util.List;
import java.util.Map;

import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.model.Movie;

public interface MovieDao extends Dao<Movie, Long> {

	List<MoviePosterDto> findAllMoviePoster(Map<String, String> sortParameters);
	List<MoviePosterDto> findRandomMoviePoster(int count);
	List<MoviePosterDto> findMoviePosterByGenre(Long genreId, Map<String, String> sortParameters);

}
