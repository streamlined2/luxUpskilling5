package org.training.springboot.movieland.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, K> {

	List<E> findAll();

	Optional<E> findById(K id);

	void save(E entity);

	void save(K id, E entity);

	void deleteById(K id);

}
