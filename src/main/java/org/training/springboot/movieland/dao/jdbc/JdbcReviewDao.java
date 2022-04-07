package org.training.springboot.movieland.dao.jdbc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.training.springboot.movieland.dao.ReviewDao;
import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.model.Review;

import static org.training.springboot.movieland.Application.DB_SCHEMA_NAME;

@Repository
public class JdbcReviewDao implements ReviewDao {

	private static final String FIND_REVIEW_STATEMENT = String.format("""
					SELECT r.movie_id, r.user_id, r.post_time, r.text
					FROM %s.review r
					WHERE r.movie_id = :movieId AND r.user_id = :userId AND post_time = :postTime
			""", DB_SCHEMA_NAME);
	private static final String FIND_ALL_REVIEWS_STATEMENT = String
			.format("SELECT r.movie_id, r.user_id, r.post_time, r.text FROM %s.review r", DB_SCHEMA_NAME);
	private static final String INSERT_REVIEW_STATEMENT = String.format(
			"INSERT INTO %s.review (movie_id, user_id, post_time, text) VALUES (:movieId, :userId, :postTime, :text)",
			DB_SCHEMA_NAME);
	private static final String UPDATE_REVIEW_STATEMENT = String.format(
			"UPDATE %s.review SET text = :text WHERE r.movie_id = :movieId AND r.user_id = :userId AND post_time = :postTime",
			DB_SCHEMA_NAME);
	private static final String DELETE_REVIEW_STATEMENT = String.format(
			"DELETE FROM %s.review WHERE r.movie_id = :movieId AND r.user_id = :userId AND post_time = :postTime",
			DB_SCHEMA_NAME);
	private static final String FIND_MOVIE_REVIEWS_STATEMENT = String.format("""
				SELECT r.movie_id, r.user_id, r.post_time, r.text, u.name
				FROM %s.review r JOIN %s.user u ON r.user_id = u.id
				WHERE r.movie_id = :id
			""", DB_SCHEMA_NAME, DB_SCHEMA_NAME);

	private static final String TEXT_FIELD = "text";
	private static final String MOVIE_FIELD = "movie_id";
	private static final String USER_FIELD = "user_id";
	private static final String POST_TIME_FIELD = "post_time";

	private static final RowMapper<Review> REVIEW_ROW_MAPPER = (resultSet, rowNum) -> Review.builder()
			.movieId(resultSet.getLong(MOVIE_FIELD)).userId(resultSet.getLong(USER_FIELD))
			.postTime(resultSet.getTimestamp(POST_TIME_FIELD).toLocalDateTime()).text(resultSet.getString(TEXT_FIELD))
			.build();
	private static final RowMapper<ReviewDto> REVIEW_DTO_MAPPER = (resultSet, rowNum) -> new ReviewDto(
			resultSet.getLong(MOVIE_FIELD), resultSet.getLong(USER_FIELD),
			resultSet.getTimestamp(POST_TIME_FIELD).toLocalDateTime(), resultSet.getString(TEXT_FIELD));

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcReviewDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Review> findAll() {
		return jdbcTemplate.query(FIND_ALL_REVIEWS_STATEMENT, Map.of(), REVIEW_ROW_MAPPER);
	}

	@Override
	public Optional<Review> findById(Long id) {
		throw new UnsupportedOperationException("no such field 'id' in table 'review'");
	}

	@Override
	public Optional<Review> findByMovieUserPostTime(Long movieId, Long userId, LocalDateTime postTime) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("movieId", movieId);
		parameterSource.addValue("userId", userId);
		parameterSource.addValue("postTime", postTime);
		return jdbcTemplate.query(FIND_REVIEW_STATEMENT, parameterSource, REVIEW_ROW_MAPPER).stream().findFirst();
	}

	@Override
	public void save(Review review) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("movieId", review.getMovieId());
		parameterSource.addValue("userId", review.getUserId());
		parameterSource.addValue("postTime", review.getPostTime());
		parameterSource.addValue("text", review.getText());
		jdbcTemplate.update(INSERT_REVIEW_STATEMENT, parameterSource);
	}

	@Override
	public void save(Long id, Review review) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("movieId", review.getMovieId());
		parameterSource.addValue("userId", review.getUserId());
		parameterSource.addValue("postTime", review.getPostTime());
		parameterSource.addValue("text", review.getText());
		jdbcTemplate.update(UPDATE_REVIEW_STATEMENT, parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException("no such field 'id' in table 'review'");
	}

	@Override
	public void deleteByMovieUserPostTime(Long movieId, Long userId, LocalDateTime postTime) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("movieId", movieId);
		parameterSource.addValue("userId", userId);
		parameterSource.addValue("postTime", postTime);
		jdbcTemplate.update(DELETE_REVIEW_STATEMENT, parameterSource);
	}

	@Override
	public List<ReviewDto> getMovieReviewsById(Long movieId) {
		return jdbcTemplate.query(FIND_MOVIE_REVIEWS_STATEMENT, new MapSqlParameterSource("id", movieId),
				REVIEW_DTO_MAPPER);
	}

}
