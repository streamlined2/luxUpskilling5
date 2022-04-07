package org.training.springboot.movieland.dto.mapper;

import org.springframework.stereotype.Component;
import org.training.springboot.movieland.dto.CountryDto;
import org.training.springboot.movieland.model.Country;

@Component
public class CountryMapper {

	public CountryDto toDto(Country country) {
		return new CountryDto(country.getId(), country.getName());
	}

	public Country toCountry(CountryDto dto) {
		return Country.builder().id(dto.id()).name(dto.name()).build();
	}

}

