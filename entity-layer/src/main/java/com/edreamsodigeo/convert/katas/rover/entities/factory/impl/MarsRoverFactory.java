package com.edreamsodigeo.convert.katas.rover.entities.factory.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.entities.impl.MarsRover;

public class MarsRoverFactory implements RoverFactory {

    private final CoordinateFactory coordinateFactory;

    public MarsRoverFactory(CoordinateFactory coordinateFactory) {
        this.coordinateFactory = coordinateFactory;
    }

    @Override
    public MarsRover create(String name, String direction, int horizontalPosition, int verticalPosition, boolean active) {
        Direction roverDirection = Direction.valueOf(direction.toUpperCase());
        Coordinate coordinate = coordinateFactory.create(horizontalPosition, verticalPosition);
        return new MarsRover(name, roverDirection, coordinate, active);
    }
}
