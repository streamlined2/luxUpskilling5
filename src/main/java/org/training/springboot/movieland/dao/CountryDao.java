package org.training.springboot.movieland.dao;

import java.util.List;

import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.model.Country;

public interface CountryDao extends Dao<Country, Long> {

	List<CountryDto> getMovieCountriesById(Long movieId);

}
