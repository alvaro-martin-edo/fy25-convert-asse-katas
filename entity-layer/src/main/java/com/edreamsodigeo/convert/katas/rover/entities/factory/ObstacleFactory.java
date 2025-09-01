package com.edreamsodigeo.convert.katas.rover.entities.factory;

import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;

public interface ObstacleFactory {
    public Obstacle create(int horizontalPosition, int verticalPosition, String type);
}
