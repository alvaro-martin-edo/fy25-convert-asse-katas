package com.kata.rover.infrastructure.adapter.commandline.output;

import com.kata.rover.model.Position;

import java.util.Objects;

public class PositionRenderer {
    // Format to render the position
    private static final String POSITION_FORMAT = "%d:%d:%s";

    private final DirectionRenderer directionRenderer;

    public PositionRenderer(DirectionRenderer directionRenderer) {
        this.directionRenderer = directionRenderer;
    }

    public String render(Position position) {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("Position is null");
        }

        return String.format(POSITION_FORMAT, position.getX(), position.getY(), directionRenderer.render(position.getDirection()));

    }


}
