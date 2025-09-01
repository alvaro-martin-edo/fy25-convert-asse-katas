package com.edreamsodigeo.convert.katas.rover.entities;

import java.util.Map;

public interface PlanetGrid {

    Integer getWidth();

    Integer getHeight();

    boolean isValid(Coordinate coordinate);

    String getName();

    Map<Coordinate, Rover> getDeployedRovers();

    Map<Coordinate, Obstacle> getObstacles();

    boolean isObstacle(Coordinate coordinate);

    boolean isRover(Coordinate coordinate);

    void addRover(Rover rover);

    void addObstacle(Obstacle obstacle);

    boolean isEmpty(Coordinate coordinate);
}
