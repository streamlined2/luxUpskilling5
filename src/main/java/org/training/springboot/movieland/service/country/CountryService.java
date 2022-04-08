package org.training.springboot.movieland.service.country;

import java.util.List;

import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.service.BasicCacheableService;

public interface CountryService extends BasicCacheableService<CountryDto> {

	List<CountryDto> getMovieCountriesById(Long movieId);
	
	void save(CountryDto dto);

	void save(Long id, CountryDto dto);

	void deleteById(Long id);

}
