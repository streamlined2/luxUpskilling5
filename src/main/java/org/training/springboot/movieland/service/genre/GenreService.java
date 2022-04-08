package org.training.springboot.movieland.service.genre;

import java.util.List;

import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.service.BasicCacheableService;

public interface GenreService extends BasicCacheableService<GenreDto> {

	List<GenreDto> getMovieGenresById(Long movieId);
	
	void save(GenreDto dto);

	void save(Long id, GenreDto dto);

	void deleteById(Long id);

}
