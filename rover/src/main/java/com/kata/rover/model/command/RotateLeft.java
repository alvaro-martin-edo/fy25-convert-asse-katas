package com.kata.rover.model.command;

import com.kata.rover.model.Direction;
import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;

public class RotateLeft implements Command {
    @Override
    public Position execute(Position position, Plateau plateau) {
        Direction direction = position.getDirection();
        return new Position(position.getX(), position.getY(), direction.rotateLeft());
    }
}
