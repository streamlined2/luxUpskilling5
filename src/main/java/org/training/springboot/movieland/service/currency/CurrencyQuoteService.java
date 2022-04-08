package org.training.springboot.movieland.service.currency;

import java.math.BigDecimal;
import java.util.Optional;

import org.training.springboot.movieland.dto.QuoteDto;

public interface CurrencyQuoteService {
	
	Optional<QuoteDto> getQuoteByCurrencyCode(String currencyCode);
	BigDecimal getCurrencySumForCode(BigDecimal sum, Optional<String> concurrencyCode);

}
