package org.training.springboot.movieland.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.model.Review;

public interface ReviewDao extends Dao<Review, Long> {

	void deleteByMovieUserPostTime(Long movieId, Long userId, LocalDateTime postTime);
	List<ReviewDto> getMovieReviewsById(Long movieId);
	Optional<Review> findByMovieUserPostTime(Long movieId, Long userId, LocalDateTime postTime);

}
