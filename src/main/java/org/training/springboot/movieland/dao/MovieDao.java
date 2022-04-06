package org.training.springboot.movieland.dao;

import java.util.List;

import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.model.Movie;

public interface MovieDao extends Dao<Movie, Long> {

	public List<MoviePosterDto> findAllMoviePoster();
	public List<MoviePosterDto> findRandomMoviePoster(int count);

}
