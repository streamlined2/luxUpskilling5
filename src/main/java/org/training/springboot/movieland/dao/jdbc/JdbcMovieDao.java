package org.training.springboot.movieland.dao.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.training.springboot.movieland.dao.MovieDao;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.model.Movie;
import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

@Repository
public class JdbcMovieDao implements MovieDao {

	private static final String FIND_MOVIE_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price FROM %s.movie m WHERE m.id = :id",
			DB_SCHEMA_NAME);
	private static final String FIND_ALL_MOVIES_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price, p.link FROM %s.movie m LEFT JOIN %s.poster p ON m.id = p.movie_id",
			DB_SCHEMA_NAME, DB_SCHEMA_NAME);
	private static final String INSERT_MOVIE_STATEMENT = String.format(
			"INSERT INTO %s.movie (title, issue_year, plot, rating, price) VALUES (:title, :issueYear, :plot, :rating, :price)",
			DB_SCHEMA_NAME);
	private static final String UPDATE_MOVIE_STATEMENT = String.format(
			"UPDATE %s.movie SET title = :title, issue_year = :issueYear, plot = :plot, rating = :rating, price = :price WHERE id = :id",
			DB_SCHEMA_NAME);
	private static final String DELETE_MOVIE_STATEMENT = String.format("DELETE FROM %s.movie WHERE id = :id",
			DB_SCHEMA_NAME);
	private static final String FIND_RANDOM_QUANTITY_MOVIES_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price, p.link FROM %s.movie m LEFT JOIN %s.poster p ON m.id = p.movie_id ORDER BY RANDOM() LIMIT :count",
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
		return jdbcTemplate.query(FIND_ALL_MOVIES_STATEMENT, Map.of(), MOVIE_ROW_MAPPER);
	}

	@Override
	public List<MoviePosterDto> findAllMoviePoster() {
		return jdbcTemplate.query(FIND_ALL_MOVIES_STATEMENT, Map.of(), MOVIE_POSTER_ROW_MAPPER);
	}

	@Override
	public List<MoviePosterDto> findRandomMoviePoster(int count) {
		return jdbcTemplate.query(FIND_RANDOM_QUANTITY_MOVIES_STATEMENT, new MapSqlParameterSource("count", count),
				MOVIE_POSTER_ROW_MAPPER);
	}

	@Override
	public Optional<Movie> findById(Long id) {
		return jdbcTemplate.query(FIND_MOVIE_STATEMENT, new MapSqlParameterSource("id", id), MOVIE_ROW_MAPPER).stream()
				.findFirst();
	}

	@Override
	public void save(Movie movie) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("title", movie.getTitle());
		parameterSource.addValue("issueYear", movie.getIssueYear());
		parameterSource.addValue("plot", movie.getPlot());
		parameterSource.addValue("rating", movie.getRating());
		parameterSource.addValue("price", movie.getPrice());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(INSERT_MOVIE_STATEMENT, parameterSource, keyHolder);
		movie.setId(keyHolder.getKeyAs(Long.class));
	}

	@Override
	public void save(Long id, Movie movie) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		parameterSource.addValue("title", movie.getTitle());
		parameterSource.addValue("issueYear", movie.getIssueYear());
		parameterSource.addValue("plot", movie.getPlot());
		parameterSource.addValue("rating", movie.getRating());
		parameterSource.addValue("price", movie.getPrice());
		jdbcTemplate.update(UPDATE_MOVIE_STATEMENT, parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(DELETE_MOVIE_STATEMENT, new MapSqlParameterSource("id", id));
	}

}
