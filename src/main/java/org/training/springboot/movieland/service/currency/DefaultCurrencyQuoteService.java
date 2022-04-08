package org.training.springboot.movieland.service.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.springboot.movieland.dto.QuoteDto;

@Service
public class DefaultCurrencyQuoteService implements CurrencyQuoteService {

	private static final String NATIONAL_BANK_EXCHANGE_RATES_ENDPOINT = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

	private final RestTemplate restTemplate;
	private final ScheduledExecutorService refresher;
	private final int refreshmentPeriodInHours;
	private final AtomicReference<List<QuoteDto>> quotes;

	public DefaultCurrencyQuoteService(RestTemplate restTemplate,
			@Value("${config.quote.refreshment.period}") int refreshmentPeriodInHours) {
		this.restTemplate = restTemplate;
		this.quotes = new AtomicReference<>();
		this.refreshmentPeriodInHours = refreshmentPeriodInHours;
		refresher = Executors.newScheduledThreadPool(1);
	}

	@PostConstruct
	private void init() {
		fetchQuotes();
		refresher.scheduleWithFixedDelay(this::fetchQuotes, refreshmentPeriodInHours, refreshmentPeriodInHours,
				TimeUnit.HOURS);
	}

	private void fetchQuotes() {
		quotes.set(new CopyOnWriteArrayList<>(
				Arrays.asList(restTemplate.getForObject(NATIONAL_BANK_EXCHANGE_RATES_ENDPOINT, QuoteDto[].class))));
	}

	@Override
	public Optional<QuoteDto> getQuoteByCurrencyCode(String currencyCode) {
		return quotes.get().stream().filter(quote -> quote.getCurrencyCode().equals(currencyCode)).findFirst();
	}

	@Override
	public BigDecimal getCurrencySumForCode(BigDecimal sum, Optional<String> concurrencyCode) {
		if (concurrencyCode.isEmpty()) {
			return sum;
		}
		Optional<QuoteDto> dto = concurrencyCode.flatMap(this::getQuoteByCurrencyCode);
		Optional<BigDecimal> value = dto.map(quote -> sum.divide(quote.getExchangeRate(), RoundingMode.HALF_UP)); 
		return value.orElseThrow(() -> new CurrencyConversionException(
						String.format("can't represent sum %s for currency code %s", sum.toString(), concurrencyCode)));
	}

}
