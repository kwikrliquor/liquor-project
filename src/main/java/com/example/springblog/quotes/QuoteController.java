package com.example.springblog.quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @GetMapping("/quotes/random")
    public Quote getRandomQuote() {
        return Quote.getRandomQuote();
    }

    @GetMapping("/quotes")
    public Quote[] getAllQuotes() {
        return Quote.getAllQuotes();
    }
}