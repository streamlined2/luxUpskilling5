package org.training.springboot.movieland.dto;

import java.math.BigDecimal;

public record MovieDto(Long id, String title, int issueYear, String plot, BigDecimal rating, BigDecimal price) {
}
