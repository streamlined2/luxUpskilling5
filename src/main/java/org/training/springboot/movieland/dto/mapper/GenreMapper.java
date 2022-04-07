package org.training.springboot.movieland.dto.mapper;

import org.springframework.stereotype.Component;
import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.model.Genre;

@Component
public class GenreMapper {

	public GenreDto toDto(Genre genre) {
		return new GenreDto(genre.getId(), genre.getName());
	}

	public Genre toGenre(GenreDto dto) {
		return Genre.builder().id(dto.id()).name(dto.name()).build();
	}

}
