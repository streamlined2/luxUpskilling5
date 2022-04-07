package org.training.springboot.movieland.service;

import org.springframework.dao.DataAccessException;

public class CacheModificationException extends DataAccessException {

	public CacheModificationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
