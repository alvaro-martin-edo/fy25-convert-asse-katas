package com.edreamsodigeo.convert.katas.rover.entities;

public interface Rover extends Positionable {

    void changeDirection(Direction direction);

    void changePosition(Coordinate coordinate);

    Direction getDirection();

    String getName();

    String getStatus();

    boolean isActive();

    Coordinate getPosition();
}
