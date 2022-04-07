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
import org.training.springboot.movieland.dao.CountryDao;
import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.model.Country;

import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

@Repository
public class JdbcCountryDao implements CountryDao {

	private static final String FIND_COUNTRY_STATEMENT = String
			.format("SELECT c.id, c.name FROM %s.country c WHERE c.id = :id", DB_SCHEMA_NAME);
	private static final String FIND_ALL_COUNTRIES_STATEMENT = String.format("SELECT c.id, c.name FROM %s.country c",
			DB_SCHEMA_NAME);
	private static final String INSERT_COUNTRY_STATEMENT = String.format("INSERT INTO %s.country (name) VALUES (:name)",
			DB_SCHEMA_NAME);
	private static final String UPDATE_COUNTRY_STATEMENT = String
			.format("UPDATE %s.country SET name = :name WHERE id = :id", DB_SCHEMA_NAME);
	private static final String DELETE_COUNTRY_STATEMENT = String.format("DELETE FROM %s.country WHERE id = :id",
			DB_SCHEMA_NAME);
	private static final String FIND_MOVIE_COUNTRIES_STATEMENT = String.format("""
				SELECT c.id, c.name
				FROM %s.country c JOIN %s.movie_country mc ON c.id = mc.country_id
				WHERE mc.movie_id = :id
			""", DB_SCHEMA_NAME, DB_SCHEMA_NAME);

	private static final String NAME_FIELD = "name";
	private static final String ID_FIELD = "id";

	private static final RowMapper<Country> COUNTRY_ROW_MAPPER = (resultSet, rowNum) -> Country.builder()
			.id(resultSet.getLong(ID_FIELD)).name(resultSet.getString(NAME_FIELD)).build();
	private static final RowMapper<CountryDto> COUNTRY_DTO_MAPPER = (resultSet,
			rowNum) -> new CountryDto(resultSet.getLong(ID_FIELD), resultSet.getString(NAME_FIELD));

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCountryDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Country> findAll() {
		return jdbcTemplate.query(FIND_ALL_COUNTRIES_STATEMENT, Map.of(), COUNTRY_ROW_MAPPER);
	}

	@Override
	public Optional<Country> findById(Long id) {
		return jdbcTemplate.query(FIND_COUNTRY_STATEMENT, new MapSqlParameterSource("id", id), COUNTRY_ROW_MAPPER)
				.stream().findFirst();
	}

	@Override
	public void save(Country country) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", country.getName());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(INSERT_COUNTRY_STATEMENT, parameterSource, keyHolder);
		country.setId(keyHolder.getKeyAs(Long.class));
	}

	@Override
	public void save(Long id, Country country) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		parameterSource.addValue("name", country.getName());
		jdbcTemplate.update(UPDATE_COUNTRY_STATEMENT, parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(DELETE_COUNTRY_STATEMENT, new MapSqlParameterSource("id", id));
	}

	@Override
	public List<CountryDto> getMovieCountriesById(Long movieId) {
		return jdbcTemplate.query(FIND_MOVIE_COUNTRIES_STATEMENT, new MapSqlParameterSource("id", movieId),
				COUNTRY_DTO_MAPPER);
	}

}
