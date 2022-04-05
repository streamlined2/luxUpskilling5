package org.training.springboot.movieland.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.training.springboot.movieland.model.Movie;
import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

@Repository
public class JdbcMovieDao implements Dao<Movie, Long> {

	private static final String FIND_ALL_STATEMENT = String
			.format("SELECT id, title, issue_year, plot, rating, price FROM %s.movie", DB_SCHEMA_NAME);

	private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (resultSet, rowNum) -> Movie.builder()
			.id(resultSet.getLong("id")).title(resultSet.getString("title")).issueYear(resultSet.getInt("issue_year"))
			.plot(resultSet.getString("plot")).rating(resultSet.getBigDecimal("rating"))
			.price(resultSet.getBigDecimal("price")).build();

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcMovieDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Movie> findAll() {
		return jdbcTemplate.query(FIND_ALL_STATEMENT, Map.of(), MOVIE_ROW_MAPPER);
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
