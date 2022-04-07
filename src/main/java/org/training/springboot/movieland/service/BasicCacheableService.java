package org.training.springboot.movieland.service;

import java.util.List;
import java.util.Optional;

public interface BasicCacheableService<D> {

	List<D> findAll();

	Optional<D> findById(Long id);

}
