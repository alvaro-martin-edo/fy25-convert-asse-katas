package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.ObstacleType;

public class CommonObstacle implements Obstacle {

    private final Coordinate position;
    private final ObstacleType type;

    public CommonObstacle(Coordinate position, ObstacleType type) {
        this.position = position;
        this.type = type;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public Integer getHorizontalPosition() {
        return getPosition().getHorizontalPosition();
    }

    @Override
    public Integer getVerticalPosition() {
        return getPosition().getVerticalPosition();
    }

    @Override
    public ObstacleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CommonObstacle{" +
                "horizontalPosition=" + getHorizontalPosition() +
                ", verticalPosition=" + getVerticalPosition() +
                ", type='" + type + '\'' +
                '}';
    }
}
