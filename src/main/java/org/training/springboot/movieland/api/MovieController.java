package org.training.springboot.movieland.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.springboot.movieland.dto.MovieDto;
import org.training.springboot.movieland.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping
	public List<MovieDto> getAll() {
		return movieService.findAll();
	}

}
