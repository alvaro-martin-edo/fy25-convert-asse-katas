package com.edreamsodigeo.convert.katas.rover.entities.factory.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.ObstacleType;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.ObstacleFactory;
import com.edreamsodigeo.convert.katas.rover.entities.impl.CommonObstacle;

public class CommonObstacleFactory implements ObstacleFactory {

    private final CoordinateFactory coordinateFactory;

    public CommonObstacleFactory(CoordinateFactory coordinateFactory) {
        this.coordinateFactory = coordinateFactory;
    }

    @Override
    public Obstacle create(int horizontalPosition, int verticalPosition, String type) {
        return new CommonObstacle(
                coordinateFactory.create(horizontalPosition, verticalPosition),
                ObstacleType.valueOf(type.toUpperCase())
        );
    }

    @Override
    public String toString() {
        return "CommonObstacleFactory{" +
                "coordinateFactory=" + coordinateFactory +
                '}';
    }
}
