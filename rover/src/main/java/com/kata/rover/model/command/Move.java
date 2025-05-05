package com.kata.rover.model.command;

import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;

public class Move implements Command {
    @Override
    public Position execute(Position position, Plateau plateau) {
        return plateau.resolvePosition(getNextTheoreticalPosition(position));
    }

    private Position getNextTheoreticalPosition(Position position) {
        return switch (position.getDirection()) {
            case NORTH -> new Position(position.getX(), position.getY() + 1, position.getDirection());
            case SOUTH -> new Position(position.getX(), position.getY() - 1, position.getDirection());
            case EAST -> new Position(position.getX() + 1, position.getY(), position.getDirection());
            case WEST -> new Position(position.getX() - 1, position.getY(), position.getDirection());
        };
    }

}
