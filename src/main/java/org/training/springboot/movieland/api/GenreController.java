package org.training.springboot.movieland.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.service.GenreService;

@RestController
@RequestMapping("api/v1/genre")
public class GenreController {

	private final GenreService genreService;

	@Autowired
	public GenreController(@Qualifier("cachedGenreService") GenreService genreService) {
		this.genreService = genreService;
	}
	
	@GetMapping
	public List<GenreDto> getAll() {
		return genreService.findAll();
	}

}
