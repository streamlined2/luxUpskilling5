package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.training.springboot.movieland.dao.jdbc.JdbcMovieDao;
import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.dto.mapper.MovieMapper;
import org.training.springboot.movieland.model.Movie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultMovieService implements MovieService {

	private final JdbcMovieDao movieDao;
	private final MovieMapper movieMapper;

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

}
