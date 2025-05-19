package com.kata.rover.infrastructure.adapter.commandline.input;

import com.kata.rover.model.command.Command;
import com.kata.rover.model.command.Move;
import com.kata.rover.model.command.RotateLeft;
import com.kata.rover.model.command.RotateRight;

public class SimpleCommandParser implements CommandParser {
    @Override
    public Command parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Could not parse command. Input cannot be null");
        }

        return switch (input) {
            case "L" -> new RotateLeft();
            case "R" -> new RotateRight();
            case "M" -> new Move();
            default -> throw new IllegalArgumentException("Invalid command: " + input);
        };
    }
}
