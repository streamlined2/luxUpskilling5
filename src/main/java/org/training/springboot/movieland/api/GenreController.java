package org.training.springboot.movieland.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.service.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/genre")
public class GenreController {
	
	private final GenreService genreService;
	
	@GetMapping
	public List<GenreDto> getAll() {
		return genreService.findAll();
	}

}
