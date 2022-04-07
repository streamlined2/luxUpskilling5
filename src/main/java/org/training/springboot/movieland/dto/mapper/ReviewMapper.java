package org.training.springboot.movieland.dto.mapper;

import org.springframework.stereotype.Component;
import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.model.Review;

@Component
public class ReviewMapper {

	public ReviewDto toDto(Review review) {
		return new ReviewDto(review.getMovieId(), review.getUserId(), review.getPostTime(), review.getText());
	}

	public Review toReview(ReviewDto dto) {
		return Review.builder().movieId(dto.movieId()).userId(dto.userId()).postTime(dto.postTime()).text(dto.text())
				.build();
	}

}
