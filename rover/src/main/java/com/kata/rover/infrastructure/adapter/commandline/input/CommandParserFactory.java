package com.kata.rover.infrastructure.adapter.commandline.input;

public class CommandParserFactory {
    private final SimpleCommandParser simpleCommandParser;
    private final RepeatedCommandParser repeatedCommandParser;

    public CommandParserFactory(SimpleCommandParser simpleCommandParser, RepeatedCommandParser repeatedCommandParser) {
        this.simpleCommandParser = simpleCommandParser;
        this.repeatedCommandParser = repeatedCommandParser;
    }

    public CommandParser fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length() > 1) {
            return repeatedCommandParser;
        }
        return simpleCommandParser;
    }
}
