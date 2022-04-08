package org.training.springboot.movieland.service;

import org.springframework.dao.DataAccessException;

public class NoDataAvailableException extends DataAccessException {

	public NoDataAvailableException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoDataAvailableException(String msg) {
		super(msg);
	}

}
