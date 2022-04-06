package org.training.springboot.movieland.dao.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.training.springboot.movieland.dao.Dao;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.model.Movie;
import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

@Repository
public class JdbcMovieDao implements Dao<Movie, Long> {

	private static final String FIND_ONE_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price FROM %s.movie m WHERE m.id = :id",
			DB_SCHEMA_NAME);
	private static final String FIND_ALL_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price, p.link FROM %s.movie m LEFT JOIN %s.poster p ON m.id = p.movie_id",
			DB_SCHEMA_NAME, DB_SCHEMA_NAME);

	private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (resultSet, rowNum) -> Movie.builder()
			.id(resultSet.getLong("id")).title(resultSet.getString("title")).issueYear(resultSet.getInt("issue_year"))
			.plot(resultSet.getString("plot")).rating(resultSet.getBigDecimal("rating"))
			.price(resultSet.getBigDecimal("price")).build();
	private static final RowMapper<MoviePosterDto> MOVIE_POSTER_ROW_MAPPER = (resultSet, rowNum) -> new MoviePosterDto(
			resultSet.getLong("id"), resultSet.getString("title"), resultSet.getInt("issue_year"),
			resultSet.getString("plot"), resultSet.getBigDecimal("rating"), resultSet.getBigDecimal("price"),
			resultSet.getString("link"));

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcMovieDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Movie> findAll() {
		return jdbcTemplate.query(FIND_ALL_STATEMENT, Map.of(), MOVIE_ROW_MAPPER);
	}

	public List<MoviePosterDto> findAllMoviePoster() {
		return jdbcTemplate.query(FIND_ALL_STATEMENT, Map.of(), MOVIE_POSTER_ROW_MAPPER);
	}

	@Override
	public Optional<Movie> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Movie dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Long id, Movie dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
