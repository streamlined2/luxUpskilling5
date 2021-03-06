package org.training.springboot.movieland.service.movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.training.springboot.movieland.dto.MovieDetailsDto;
import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.service.BasicCacheableService;

public interface MovieService extends BasicCacheableService<MovieDto> {

	List<MoviePosterDto> findAllMoviePoster(Map<String, String> sortParameters);

	List<MoviePosterDto> findRandomMoviePoster(int count);

	List<MoviePosterDto> getMoviePosterByGenre(Long genreId, Map<String, String> sortParameters);

	Optional<MovieDetailsDto> getMovieDetailsById(Long movieId, Optional<String> currencyCode);

	void save(MovieDto dto);

	void save(Long id, MovieDto dto);

	void deleteById(Long id);

}
