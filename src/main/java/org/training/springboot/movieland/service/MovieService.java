package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.dto.MoviePosterDto;

public interface MovieService extends BasicCacheableService<MovieDto> {

	List<MoviePosterDto> findAllMoviePoster();

	List<MoviePosterDto> findRandomMoviePoster(int count);

	List<MoviePosterDto> getMoviePosterByGenre(Long genreId);

	Optional<MovieDto> findById(Long id);

	void save(MovieDto dto);

	void save(Long id, MovieDto dto);

	void deleteById(Long id);

}
