package org.training.springboot.movieland.service;

import java.util.List;

public interface BasicCacheableService<D> {

	List<D> findAll();

}
