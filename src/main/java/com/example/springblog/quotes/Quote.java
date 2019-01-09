package com.example.springblog.quotes;

public class Quote {
    private final String quote;
    private final String author;
    private static Quote[] quotes = {
            new Quote("anonymous", "Programming is a way of thinking"),
            new Quote("Dolly Parton", "We cannot direct the wind, but we can adjust the sails."),
            new Quote("anonymous", "To go fast, you must first go slow."),
            new Quote("Yogi Berra", "When you come to a fork in the road, take it!"),
            new Quote("Julia Evans", "I think that making things easy is sort-of like a way to \"invent\" a new feature for people!"),
            new Quote("Margaret Hamilton", "There was no choice but to be pioneers; no time to be beginners."),
            new Quote("anonymous", "Momentum begets momentum!")
    };

    public Quote(String author, String quote) {
        this.quote = quote;
        this.author = author;
    }

    public static Quote[] getAllQuotes() {
        return quotes;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public static Quote getRandomQuote() {


        int randomIndex = (int) Math.floor(Math.random() * quotes.length);

        Quote randomQuote = quotes[randomIndex];

        return randomQuote;

    }
}
