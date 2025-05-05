package com.kata.rover.infrastructure.adapter.commandline.input;

import com.kata.rover.model.command.Command;
import com.kata.rover.model.command.Move;
import com.kata.rover.model.command.RotateLeft;
import com.kata.rover.model.command.RotateRight;

import java.util.List;
import java.util.Optional;

public class CommandParser {

    public List<Command> parseCommands(String input) {
        return Optional.ofNullable(input)
            .stream()
            .flatMapToInt(String::chars)
            .mapToObj(c -> (char) c)
            .map(this::parseSingleCommand)
            .toList();
    }

    private Command parseSingleCommand(Character input) {
        if (input == null) {
            return null;
        }

        return switch (input) {
            case 'L' -> new RotateLeft();
            case 'R' -> new RotateRight();
            case 'M' -> new Move();
            default -> null;
        };
    }

}
