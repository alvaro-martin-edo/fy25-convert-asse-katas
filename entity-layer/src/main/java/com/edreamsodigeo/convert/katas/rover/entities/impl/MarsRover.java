package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;

public class MarsRover implements Rover {

    private Coordinate position;
    private Direction direction;
    private String name;
    private boolean active;

    public MarsRover(String name, Direction direction, Coordinate coordinate, boolean active) {
        this.name = name;
        this.direction = direction;
        this.position = coordinate;
        this.active = active;
    }

    @Override
    public void changeDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    @Override
    public void changePosition(Coordinate newCoordinates) {
        if (newCoordinates.isValid(null)) {
            this.position = newCoordinates;
        } else {
            throw new IllegalArgumentException("Invalid coordinate: " + newCoordinates);
        }
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public Integer getHorizontalPosition() {
        return position.getHorizontalPosition();
    }

    @Override
    public Integer getVerticalPosition() {
        return position.getVerticalPosition();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return "Rover " + name + " is at position " + direction + ":" + getHorizontalPosition() + ":" + getVerticalPosition();
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
