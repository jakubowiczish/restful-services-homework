package com.distributedsystems.restfulserviceshomework.controller.quote.html;

import com.distributedsystems.restfulserviceshomework.request.quote.QuoteRequest;
import com.distributedsystems.restfulserviceshomework.response.quote.QuoteResponse;
import com.distributedsystems.restfulserviceshomework.service.quote.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/quotes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuoteHtmlController {

    private QuoteService quoteService;

    @GetMapping("/nRandomQuotes")
    public String getNRandomQuotes(Model model) {
        model.addAttribute("request", new QuoteRequest());
        return "get_n_random_quotes_form";
    }

    @GetMapping("/nRandomQuotesResult")
    public String showNRandomQuotesResult(@RequestParam int numberOfQuotes, Model model) {
        List<QuoteResponse> quotes = quoteService.getNRandomQuotes(numberOfQuotes);
        model.addAttribute("quotes", quotes);
        return "get_n_random_quotes_result_form";
    }
}
