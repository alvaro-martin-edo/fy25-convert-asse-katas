package com.kata.rover.infrastructure.adapter.commandline.input;

import com.kata.rover.model.command.Command;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CommandListParser {
    private static final Pattern COMMANDS_REGEX_PATTERN = Pattern.compile("((\\d+)?[LRM])");

    private final CommandParserFactory commandParserFactory;
    private final Tokenizer tokenizer;

    public CommandListParser(CommandParserFactory commandParserFactory,
                             Tokenizer tokenizer) {
        this.commandParserFactory = commandParserFactory;
        this.tokenizer = tokenizer;
    }

    public List<Command> parseCommands(String input) {
        return Optional.ofNullable(input)
                .map(tokenizer::tokenize)
                .orElse(List.of())
                .stream()
                .map(this::parseSingleCommand)
                .toList();
    }

    private Command parseSingleCommand(String input) {
        return Optional.ofNullable(input)
                .map(commandLiteral -> commandParserFactory.fromString(commandLiteral).parse(commandLiteral))
                .orElseThrow(() -> new IllegalArgumentException("Could not parse command: " + input));
    }

    private List<String> tokenize(String input) {
        return Optional.ofNullable(input)
                .map(COMMANDS_REGEX_PATTERN::matcher)
                .map(Matcher::results)
                .orElse(Stream.of())
                .map(result -> result.group(0))
                .toList();
    }

}
