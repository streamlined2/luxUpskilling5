package org.training.springboot.movieland.api.sort;

import java.util.Optional;
import java.util.stream.Stream;

public enum Order {

	ASCENDING("asc"), DESCENDING("desc");

	private final String label;

	private Order(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static Optional<Order> findByLabel(String label) {
		return Stream.of(values()).filter(option -> option.getLabel().equalsIgnoreCase(label)).findFirst();
	}

}
