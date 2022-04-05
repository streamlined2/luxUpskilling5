package org.training.springboot.movieland.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Movie {

	private Long id;
	private String title;
	private int issueYear;
	private String plot;
	private BigDecimal rating;
	private BigDecimal price;

}
