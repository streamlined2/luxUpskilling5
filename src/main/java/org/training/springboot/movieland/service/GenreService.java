package org.training.springboot.movieland.service;

import java.util.Optional;

import org.training.springboot.movieland.dto.GenreDto;

public interface GenreService extends BasicCacheableService<GenreDto> {

	Optional<GenreDto> findById(Long id);

	void save(GenreDto dto);

	void save(Long id, GenreDto dto);

	void deleteById(Long id);

}
