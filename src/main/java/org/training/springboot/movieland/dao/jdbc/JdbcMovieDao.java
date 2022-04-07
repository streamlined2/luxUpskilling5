package org.training.springboot.movieland.dao.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.training.springboot.movieland.api.sort.Order;
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
	private static final String FIND_ALL_MOVIES_BY_GENRE_STATEMENT = String.format(
			"SELECT m.id, m.title, m.issue_year, m.plot, m.rating, m.price, p.link FROM %s.movie m LEFT JOIN %s.poster p ON m.id = p.movie_id JOIN %s.movie_genre mg ON m.id = mg.movie_id WHERE mg.genre_id = :genreId",
			DB_SCHEMA_NAME, DB_SCHEMA_NAME, DB_SCHEMA_NAME);

	private static final String LINK_FIELD = "link";
	private static final String PLOT_FIELD = "plot";
	private static final String ISSUE_YEAR_FIELD = "issue_year";
	private static final String TITLE_FIELD = "title";
	private static final String ID_FIELD = "id";
	private static final String PRICE_FIELD = "price";
	private static final String RATING_FIELD = "rating";
	
	private static final List<String> orderFields = List.of(RATING_FIELD, PRICE_FIELD);

	private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (resultSet, rowNum) -> Movie.builder()
			.id(resultSet.getLong(ID_FIELD)).title(resultSet.getString(TITLE_FIELD)).issueYear(resultSet.getInt(ISSUE_YEAR_FIELD))
			.plot(resultSet.getString(PLOT_FIELD)).rating(resultSet.getBigDecimal(RATING_FIELD))
			.price(resultSet.getBigDecimal(PRICE_FIELD)).build();
	private static final RowMapper<MoviePosterDto> MOVIE_POSTER_ROW_MAPPER = (resultSet, rowNum) -> new MoviePosterDto(
			resultSet.getLong(ID_FIELD), resultSet.getString(TITLE_FIELD), resultSet.getInt(ISSUE_YEAR_FIELD),
			resultSet.getString(PLOT_FIELD), resultSet.getBigDecimal(RATING_FIELD), resultSet.getBigDecimal(PRICE_FIELD),
			resultSet.getString(LINK_FIELD));

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
	public List<MoviePosterDto> findAllMoviePoster(Map<String, String> sortParameters) {
		return jdbcTemplate.query(String.format("%s %s", FIND_ALL_MOVIES_STATEMENT, getOrderClause(sortParameters)),
				Map.of(), MOVIE_POSTER_ROW_MAPPER);
	}

	private String getOrderClause(Map<String, String> sortParameters) {
		StringBuilder b = new StringBuilder("ORDER BY ");
		int count = 0;
		for (var entry : sortParameters.entrySet()) {
			if (isCorrectSortParameter(entry)) {
				b.append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
				count++;
			}
		}
		if (count > 0) {
			b.deleteCharAt(b.length()-1);
			return b.toString();
		}
		return "";
	}

	private boolean isCorrectSortParameter(Entry<String, String> entry) {
		return orderFields.contains(entry.getKey()) && Order.findByLabel(entry.getValue()).isPresent();
	}

	@Override
	public List<MoviePosterDto> findRandomMoviePoster(int count) {
		return jdbcTemplate.query(FIND_RANDOM_QUANTITY_MOVIES_STATEMENT, new MapSqlParameterSource("count", count),
				MOVIE_POSTER_ROW_MAPPER);
	}

	@Override
	public List<MoviePosterDto> findMoviePosterByGenre(Long genreId, Map<String, String> sortParameters) {
		return jdbcTemplate.query(
				String.format("%s %s", FIND_ALL_MOVIES_BY_GENRE_STATEMENT, getOrderClause(sortParameters)),
				new MapSqlParameterSource("genreId", genreId), MOVIE_POSTER_ROW_MAPPER);
	}

	@Override
	public Optional<Movie> findById(Long id) {
		return jdbcTemplate.query(FIND_MOVIE_STATEMENT, new MapSqlParameterSource(ID_FIELD, id), MOVIE_ROW_MAPPER).stream()
				.findFirst();
	}

	@Override
	public void save(Movie movie) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(TITLE_FIELD, movie.getTitle());
		parameterSource.addValue("issueYear", movie.getIssueYear());
		parameterSource.addValue(PLOT_FIELD, movie.getPlot());
		parameterSource.addValue(RATING_FIELD, movie.getRating());
		parameterSource.addValue(PRICE_FIELD, movie.getPrice());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(INSERT_MOVIE_STATEMENT, parameterSource, keyHolder);
		movie.setId(keyHolder.getKeyAs(Long.class));
	}

	@Override
	public void save(Long id, Movie movie) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(ID_FIELD, id);
		parameterSource.addValue(TITLE_FIELD, movie.getTitle());
		parameterSource.addValue("issueYear", movie.getIssueYear());
		parameterSource.addValue(PLOT_FIELD, movie.getPlot());
		parameterSource.addValue(RATING_FIELD, movie.getRating());
		parameterSource.addValue(PRICE_FIELD, movie.getPrice());
		jdbcTemplate.update(UPDATE_MOVIE_STATEMENT, parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(DELETE_MOVIE_STATEMENT, new MapSqlParameterSource(ID_FIELD, id));
	}

}
