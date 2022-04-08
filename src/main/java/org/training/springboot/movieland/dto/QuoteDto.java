package org.training.springboot.movieland.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "currencyCode")
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class QuoteDto {

	@JsonProperty("txt")
	private String currencyName;
	@JsonProperty("rate")
	private BigDecimal exchangeRate;
	@JsonProperty("cc")
	private String currencyCode;

}
