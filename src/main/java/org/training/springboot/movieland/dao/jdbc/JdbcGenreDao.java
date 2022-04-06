package org.training.springboot.movieland.dao.jdbc;

import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

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
import org.training.springboot.movieland.dao.GenreDao;
import org.training.springboot.movieland.model.Genre;

@Repository
public class JdbcGenreDao implements GenreDao {

	private static final String FIND_GENRE_STATEMENT = String
			.format("SELECT g.id, g.name FROM %s.genre g WHERE g.id = :id", DB_SCHEMA_NAME);
	private static final String FIND_ALL_MOVIES_STATEMENT = String.format("SELECT g.id, g.name FROM %s.genre g",
			DB_SCHEMA_NAME);
	private static final String INSERT_GENRE_STATEMENT = String.format("INSERT INTO %s.genre (name) VALUES (:name)",
			DB_SCHEMA_NAME);
	private static final String UPDATE_GENRE_STATEMENT = String
			.format("UPDATE %s.genre SET name = :name WHERE id = :id", DB_SCHEMA_NAME);
	private static final String DELETE_GENRE_STATEMENT = String.format("DELETE FROM %s.genre WHERE id = :id",
			DB_SCHEMA_NAME);

	private static final RowMapper<Genre> GENRE_ROW_MAPPER = (resultSet, rowNum) -> Genre.builder()
			.id(resultSet.getLong("id")).name(resultSet.getString("name")).build();

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcGenreDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Genre> findAll() {
		return jdbcTemplate.query(FIND_ALL_MOVIES_STATEMENT, Map.of(), GENRE_ROW_MAPPER);
	}

	@Override
	public Optional<Genre> findById(Long id) {
		return jdbcTemplate.query(FIND_GENRE_STATEMENT, new MapSqlParameterSource("id", id), GENRE_ROW_MAPPER).stream()
				.findFirst();
	}

	@Override
	public void save(Genre genre) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", genre.getName());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(INSERT_GENRE_STATEMENT, parameterSource, keyHolder);
		genre.setId(keyHolder.getKeyAs(Long.class));
	}

	@Override
	public void save(Long id, Genre genre) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		parameterSource.addValue("title", genre.getName());
		jdbcTemplate.update(UPDATE_GENRE_STATEMENT, parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(DELETE_GENRE_STATEMENT, new MapSqlParameterSource("id", id));
	}

}
