package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.dto.MoviePosterDto;

public interface MovieService {

	List<MovieDto> findAll();

	List<MoviePosterDto> findAllMoviePoster();

	List<MoviePosterDto> findRandomMoviePoster(int count);

	List<MoviePosterDto> getMoviePosterByGenre(Long genreId);

	Optional<MovieDto> findById(Long id);

	void save(MovieDto tagDto);

	void save(Long id, MovieDto tagDto);

	void deleteById(Long id);

}
