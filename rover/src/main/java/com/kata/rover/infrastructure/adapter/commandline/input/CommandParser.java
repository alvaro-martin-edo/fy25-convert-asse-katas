package com.kata.rover.infrastructure.adapter.commandline.input;

import com.kata.rover.model.command.Command;

public interface CommandParser {
    Command parse(String command);
}
