package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess;

public interface ObstacleResponseDS {
    Integer getHorizontalPosition();

    Integer getVerticalPosition();

    String getObstacleType();
}
