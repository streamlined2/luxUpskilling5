package org.training.springboot.movieland.dto;

import java.math.BigDecimal;
import java.util.List;

public record MovieDetailsDto(Long id, String title, int issueYear, String plot, BigDecimal rating, BigDecimal price,
		String link, List<CountryDto> countries, List<GenreDto> genres, List<ReviewDto> reviews) {
}