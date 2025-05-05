package com.kata.rover.infrastructure.adapter.commandline.output;

import com.kata.rover.model.Direction;

public class DirectionRenderer {
    public String render(Direction direction) {
        if (direction == null) {
            return "Unknown direction";
        }
        return switch (direction) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }
}
