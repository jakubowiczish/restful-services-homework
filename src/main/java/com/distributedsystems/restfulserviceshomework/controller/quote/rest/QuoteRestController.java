package com.distributedsystems.restfulserviceshomework.controller.quote.rest;

import com.distributedsystems.restfulserviceshomework.model.quote.internal.Quote;
import com.distributedsystems.restfulserviceshomework.service.quote.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuoteRestController {

    private QuoteService quoteService;

    @GetMapping("/nRandomQuotes/{numberOfQuotes}")
    public ResponseEntity<List<Quote>> getNRandomQuotes(@PathVariable int numberOfQuotes) {
        return ResponseEntity.ok(quoteService.getNRandomQuotes(numberOfQuotes));
    }
}
