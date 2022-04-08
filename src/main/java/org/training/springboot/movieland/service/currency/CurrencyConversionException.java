package org.training.springboot.movieland.service.currency;

public class CurrencyConversionException extends RuntimeException {

	public CurrencyConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrencyConversionException(String message) {
		super(message);
	}

}
