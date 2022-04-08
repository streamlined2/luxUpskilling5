package org.training.springboot.movieland.service.genre;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.training.springboot.movieland.dao.GenreDao;
import org.training.springboot.movieland.dto.GenreDto;
import org.training.springboot.movieland.dto.mapper.GenreMapper;
import org.training.springboot.movieland.model.Genre;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("defaultGenreService")
public class DefaultGenreService implements GenreService {

	private final GenreDao genreDao;
	private final GenreMapper genreMapper;

	@Override
	public List<GenreDto> findAll() {
		return genreDao.findAll().stream().map(genreMapper::toDto).toList();
	}

	@Override
	public Optional<GenreDto> findById(Long id) {
		return genreDao.findById(id).map(genreMapper::toDto);
	}

	@Override
	public void save(GenreDto genreDto) {
		genreDao.save(genreMapper.toGenre(genreDto));		
	}

	@Override
	public void save(Long id, GenreDto genreDto) {
		Genre genre = genreMapper.toGenre(genreDto);
		genre.setId(id);
		genreDao.save(genre);
	}

	@Override
	public void deleteById(Long id) {
		genreDao.deleteById(id);
	}

	@Override
	public List<GenreDto> getMovieGenresById(Long movieId) {
		return genreDao.getMovieGenresById(movieId);
	}

}
