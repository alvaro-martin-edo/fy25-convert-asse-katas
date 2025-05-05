package com.kata.rover.model.command;

import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;

public interface Command {
    Position execute(Position position, Plateau plateau);
}
