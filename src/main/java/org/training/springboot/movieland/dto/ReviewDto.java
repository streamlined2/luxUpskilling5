package org.training.springboot.movieland.dto;

import java.time.LocalDateTime;

public record ReviewDto(Long movieId, Long userId, LocalDateTime postTime, String text) {
}
