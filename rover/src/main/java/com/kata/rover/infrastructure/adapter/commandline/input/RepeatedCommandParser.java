package com.kata.rover.infrastructure.adapter.commandline.input;

import com.kata.rover.model.command.Command;
import com.kata.rover.model.command.RepeatedCommand;

import java.util.regex.Pattern;

public class RepeatedCommandParser implements CommandParser {
    private static final Pattern REPEATED_COMMAND_REGEX = Pattern.compile("(\\d+)([LRM])");
    private final SimpleCommandParser simpleCommandParser;

    public RepeatedCommandParser(SimpleCommandParser simpleCommandParser) {
        this.simpleCommandParser = simpleCommandParser;
    }

    public RepeatedCommand<?> parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Could not parse repeated command. Input cannot be null");
        }

        var matcher = REPEATED_COMMAND_REGEX.matcher(input);
        if (matcher.matches()) {
            int times = Integer.parseInt(matcher.group(1));
            String commandLiteral = String.valueOf(matcher.group(2).charAt(0));
            Command simpleCommand = simpleCommandParser.parse(commandLiteral);
            return new RepeatedCommand<>(simpleCommand, times);
        } else {
            throw new IllegalArgumentException("Invalid repeated command: " + input);
        }
    }
}
