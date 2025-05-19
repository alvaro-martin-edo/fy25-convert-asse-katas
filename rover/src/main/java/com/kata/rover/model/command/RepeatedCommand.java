package com.kata.rover.model.command;

import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;

// Polymorphism and composition over inheritance
public class RepeatedCommand<C extends Command> implements Command {
    private final C command;
    private final int times;

    public RepeatedCommand(C command, int times) {
        if (times < 1) {
            throw new IllegalArgumentException("Times must be greater than 0");
        }
        this.command = command;
        this.times = times;
    }

    @Override
    public Position execute(Position position, Plateau plateau) {
        return repeat(command, times, position, plateau);
    }

    private Position repeat(C command, int times, Position position, Plateau plateau) {
        Position newPosition = position;
        for (int i = 0; i < times; i++) {
            newPosition = command.execute(newPosition, plateau);
        }
        return newPosition;
    }
}
