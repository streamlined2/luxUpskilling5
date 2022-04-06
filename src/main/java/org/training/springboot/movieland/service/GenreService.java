package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

import org.training.springboot.movieland.dto.GenreDto;

public interface GenreService {

	List<GenreDto> findAll();

	Optional<GenreDto> findById(Long id);

	void save(GenreDto tagDto);

	void save(Long id, GenreDto tagDto);

	void deleteById(Long id);

}
