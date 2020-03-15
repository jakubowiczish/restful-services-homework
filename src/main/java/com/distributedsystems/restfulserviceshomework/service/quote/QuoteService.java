package com.distributedsystems.restfulserviceshomework.service.quote;

import com.distributedsystems.restfulserviceshomework.model.quote.Quote;
import com.distributedsystems.restfulserviceshomework.response.quote.QuoteResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.IntStream;

import static com.distributedsystems.restfulserviceshomework.util.Constants.QUOTES_ALL_QUOTES_URL;
import static com.distributedsystems.restfulserviceshomework.util.Constants.QUOTES_RANDOM;
import static java.util.stream.Collectors.toList;

@Service
public class QuoteService {

    public List<QuoteResponse> getNRandomQuotes(int n) {
        List<Quote> quotes = IntStream.range(0, n)
                .mapToObj(i -> new RestTemplate()
                        .getForEntity(getRandomQuoteUri(), Quote.class)
                        .getBody())
                .collect(toList());

        return quotes.stream()
                .map(this::convert)
                .collect(toList());
    }

    private String getRandomQuoteUri() {
        return UriComponentsBuilder
                .fromHttpUrl(QUOTES_ALL_QUOTES_URL + QUOTES_RANDOM)
                .build()
                .toUriString();
    }

    private QuoteResponse convert(Quote quote) {
        return QuoteResponse.builder()
                .author(quote.getAuthor())
                .quote(quote.getQuote())
                .rating(quote.getRating())
                .build();
    }
}
