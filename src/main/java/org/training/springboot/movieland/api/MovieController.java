package org.training.springboot.movieland.api;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.springboot.movieland.dto.MoviePosterDto;
import org.training.springboot.movieland.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieController {

	private final MovieService movieService;

	@GetMapping
	public List<MoviePosterDto> getAllMoviePoster(@RequestParam Map<String, String> sortParameters) {
		return movieService.findAllMoviePoster(sortParameters);
	}

	@GetMapping("/random")
	public List<MoviePosterDto> getRandomMoviePoster(@Value("${config.random.count}") int count) {
		return movieService.findRandomMoviePoster(count);
	}

	@GetMapping("/genre/{genreId}")
	public List<MoviePosterDto> getMoviePosterByGenre(@PathVariable Long genreId,
			@RequestParam Map<String, String> sortParameters) {
		return movieService.getMoviePosterByGenre(genreId, sortParameters);
	}

}
