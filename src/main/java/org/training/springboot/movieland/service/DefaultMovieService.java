package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.springboot.movieland.dao.MovieDao;
import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.dto.MovieDetailsDto;
import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.dto.mapper.MovieMapper;
import org.training.springboot.movieland.model.Movie;

@Service
public class DefaultMovieService implements MovieService {

	private final MovieDao movieDao;
	private final MovieMapper movieMapper;
	private final GenreService genreService;
	private final CountryService countryService;
	private final ReviewService reviewService;

	public DefaultMovieService(MovieDao movieDao, MovieMapper movieMapper,
			@Qualifier("cachedGenreService") GenreService genreService, CountryService countryService,
			ReviewService reviewService) {
		this.movieDao = movieDao;
		this.movieMapper = movieMapper;
		this.genreService = genreService;
		this.countryService = countryService;
		this.reviewService = reviewService;
	}

	@Override
	public List<MovieDto> findAll() {
		return movieDao.findAll().stream().map(movieMapper::toDto).toList();
	}

	@Override
	public List<MoviePosterDto> findAllMoviePoster(Map<String, String> sortParameters) {
		return movieDao.findAllMoviePoster(sortParameters);
	}

	@Override
	public List<MoviePosterDto> findRandomMoviePoster(int count) {
		return movieDao.findRandomMoviePoster(count);
	}

	@Override
	public List<MoviePosterDto> getMoviePosterByGenre(Long genreId, Map<String, String> sortParameters) {
		return movieDao.findMoviePosterByGenre(genreId, sortParameters);
	}

	@Override
	public Optional<MovieDto> findById(Long id) {
		return movieDao.findById(id).map(movieMapper::toDto);
	}

	@Override
	public void save(MovieDto movieDto) {
		movieDao.save(movieMapper.toMovie(movieDto));
	}

	@Override
	public void save(Long id, MovieDto movieDto) {
		Movie movie = movieMapper.toMovie(movieDto);
		movie.setId(id);
		movieDao.save(movie);
	}

	@Override
	public void deleteById(Long id) {
		movieDao.deleteById(id);
	}

	@Transactional
	@Override
	public Optional<MovieDetailsDto> getMovieDetailsById(Long movieId) {
		Optional<MoviePosterDto> movieDto = movieDao.getMoviePosterById(movieId);
		if (movieDto.isPresent()) {
			MoviePosterDto movie = movieDto.get();
			List<CountryDto> countries = countryService.getMovieCountriesById(movieId);
			List<GenreDto> genres = genreService.getMovieGenresById(movieId);
			List<ReviewDto> reviews = reviewService.getMovieReviewsById(movieId);
			return Optional.of(new MovieDetailsDto(movie.id(), movie.title(), movie.issueYear(), movie.plot(),
					movie.rating(), movie.price(), movie.link(), countries, genres, reviews));
		}
		return Optional.empty();
	}

}
