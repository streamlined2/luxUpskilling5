package org.training.springboot.movieland.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, K> {

	List<E> findAll();

	Optional<E> findById(K id);

	void save(E dto);

	void save(K id, E dto);

	void deleteById(K id);

}
