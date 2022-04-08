package org.training.springboot.movieland.service.review;

import java.util.List;

import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.service.BasicCacheableService;

public interface ReviewService extends BasicCacheableService<ReviewDto> {

	List<ReviewDto> getMovieReviewsById(Long movieId);

	void save(ReviewDto dto);

	void save(Long id, ReviewDto dto);

	void deleteById(Long id);

}
