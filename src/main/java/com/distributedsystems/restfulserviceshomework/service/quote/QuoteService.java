package com.distributedsystems.restfulserviceshomework.service.quote;

import com.distributedsystems.restfulserviceshomework.model.quote.external.QuoteExternal;
import com.distributedsystems.restfulserviceshomework.model.quote.internal.Quote;
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

    public List<Quote> getNRandomQuotes(int n) {
        List<QuoteExternal> quoteExternalRespons = IntStream.range(0, n)
                .mapToObj(i -> new RestTemplate()
                        .getForEntity(getRandomQuoteUri(), QuoteExternal.class)
                        .getBody())
                .collect(toList());

        return quoteExternalRespons.stream()
                .map(this::convert)
                .collect(toList());
    }

    private String getRandomQuoteUri() {
        return UriComponentsBuilder
                .fromHttpUrl(QUOTES_ALL_QUOTES_URL + QUOTES_RANDOM)
                .build()
                .toUriString();
    }

    private Quote convert(QuoteExternal quoteExternal) {
        return Quote.builder()
                .author(quoteExternal.getAuthor())
                .quote(quoteExternal.getQuote())
                .rating(quoteExternal.getRating())
                .build();
    }
}
