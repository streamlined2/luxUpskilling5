package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.training.springboot.movieland.dao.CountryDao;
import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.dto.mapper.CountryMapper;
import org.training.springboot.movieland.model.Country;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultCountryService implements CountryService {

	private final CountryDao countryDao;
	private final CountryMapper countryMapper;

	@Override
	public List<CountryDto> findAll() {
		return countryDao.findAll().stream().map(countryMapper::toDto).toList();
	}

	@Override
	public Optional<CountryDto> findById(Long id) {
		return countryDao.findById(id).map(countryMapper::toDto);
	}

	@Override
	public void save(CountryDto countryDto) {
		countryDao.save(countryMapper.toCountry(countryDto));		
	}

	@Override
	public void save(Long id, CountryDto countryDto) {
		Country country = countryMapper.toCountry(countryDto);
		country.setId(id);
		countryDao.save(country);
	}

	@Override
	public void deleteById(Long id) {
		countryDao.deleteById(id);
	}

	@Override
	public List<CountryDto> getMovieCountriesById(Long movieId) {
		return countryDao.getMovieCountriesById(movieId);
	}

}
