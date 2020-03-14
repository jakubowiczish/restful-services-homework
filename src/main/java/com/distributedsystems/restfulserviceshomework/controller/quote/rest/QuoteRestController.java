package com.distributedsystems.restfulserviceshomework.controller.quote.rest;

import com.distributedsystems.restfulserviceshomework.response.quote.QuoteResponse;
import com.distributedsystems.restfulserviceshomework.service.quote.QuoteGatheringService;
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

    private QuoteGatheringService quoteGatheringService;

    @GetMapping("/nRandomQuotes/{numberOfQuotes}")
    public ResponseEntity<List<QuoteResponse>> getNRandomQuotes(@PathVariable int numberOfQuotes) {
        return ResponseEntity.ok(quoteGatheringService.getNRandomQuotes(numberOfQuotes));
    }
}
