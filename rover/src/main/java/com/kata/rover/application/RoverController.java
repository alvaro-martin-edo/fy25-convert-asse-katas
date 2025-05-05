package com.kata.rover.application;

import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;
import com.kata.rover.model.command.Command;

import java.util.List;
import java.util.Optional;

public class RoverController {
    private Position position;
    private final Plateau plateau;

    public RoverController(Position position, Plateau plateau) {
        this.position = position;
        this.plateau = plateau;
    }

    public void executeCommandList(List<Command> commands) {
        Optional.ofNullable(commands)
            .orElse(List.of())
            .forEach(this::executeCommand);
    }

    public void executeCommand(Command command) {
        position = command.execute(position, plateau);
    }

    public Position getPosition() {
        return position;
    }

}
