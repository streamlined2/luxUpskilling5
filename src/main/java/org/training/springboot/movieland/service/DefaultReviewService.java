package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.training.springboot.movieland.dao.ReviewDao;
import org.training.springboot.movieland.dto.ReviewDto;
import org.training.springboot.movieland.dto.mapper.ReviewMapper;
import org.training.springboot.movieland.model.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultReviewService implements ReviewService {

	private final ReviewDao reviewDao;
	private final ReviewMapper reviewMapper;

	@Override
	public List<ReviewDto> findAll() {
		return reviewDao.findAll().stream().map(reviewMapper::toDto).toList();
	}

	@Override
	public Optional<ReviewDto> findById(Long id) {
		return reviewDao.findById(id).map(reviewMapper::toDto);
	}

	@Override
	public void save(ReviewDto reviewDto) {
		reviewDao.save(reviewMapper.toReview(reviewDto));		
	}

	@Override
	public void save(Long id, ReviewDto reviewDto) {
		Review review = reviewMapper.toReview(reviewDto);
		reviewDao.save(review);
	}

	@Override
	public void deleteById(Long id) {
		reviewDao.deleteById(id);
	}

	@Override
	public List<ReviewDto> getMovieReviewsById(Long movieId) {
		return reviewDao.getMovieReviewsById(movieId);
	}

}
