package com.kata.rover.infrastructure.adapter.commandline.input;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Tokenizer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("(\\d+[LRM]|[LRM])");

    public List<String> tokenize(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        Matcher matcher = TOKEN_PATTERN.matcher(input);
        List<String> tokens = Optional.of(matcher)
                .map(Matcher::results)
                .orElse(Stream.of())
                .map(result -> result.group(0))
                .toList();

        String reconstructedInput = String.join("", tokens);
        if (!reconstructedInput.equals(input)) {
            throw new IllegalArgumentException("Input string contains invalid characters or format.");
        }

        return tokens;
    }
}