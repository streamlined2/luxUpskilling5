package org.training.springboot.movieland.dto;

import org.springframework.stereotype.Component;
import org.training.springboot.movieland.model.Movie;

@Component
public class MovieMapper {

	public MovieDto toDto(Movie movie) {
		return new MovieDto(movie.getId(), movie.getTitle(), movie.getIssueYear(), movie.getPlot(), movie.getRating(),
				movie.getPrice());
	}

	public Movie toMovie(MovieDto dto) {
		return Movie.builder().id(dto.id()).title(dto.title()).issueYear(dto.issueYear()).plot(dto.plot())
				.rating(dto.rating()).price(dto.price()).build();
	}

}
