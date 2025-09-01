package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;

import java.util.HashMap;
import java.util.Map;

public class StandardPlanetGrid implements PlanetGrid {

    private final int width ;
    private final int height;
    private final String name;
    private final Map<Coordinate, Rover> deployedRovers;
    private final Map<Coordinate, Obstacle> obstacles;

    public StandardPlanetGrid(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.deployedRovers = new HashMap<>();
        this.obstacles = new HashMap<>();
    }

    public StandardPlanetGrid(int width, int height, String planetName, Map<Coordinate, Rover> deployedRovers, Map<Coordinate, Obstacle> obstacles) {
        this.width = width;
        this.height = height;
        this.name = planetName;
        this.deployedRovers = deployedRovers != null ? deployedRovers : new HashMap<>();
        this.obstacles = obstacles != null ? obstacles : new HashMap<>();
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public boolean isValid(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        return coordinate.getHorizontalPosition() >= 0 && coordinate.getHorizontalPosition() < width &&
                coordinate.getVerticalPosition() >= 0 && coordinate.getVerticalPosition() < height;
    }

    @Override
    public boolean isObstacle(Coordinate coordinate) {
        return obstacles.containsKey(coordinate);
    }

    @Override
    public boolean isRover(Coordinate coordinate) {
        return deployedRovers.containsKey(coordinate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<Coordinate, Rover> getDeployedRovers() {
        return deployedRovers;
    }

    @Override
    public Map<Coordinate, Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public void addRover(Rover rover) {
        if (rover != null && isEmpty(rover.getPosition())) {
            deployedRovers.put(rover.getPosition(), rover);
        } else {
            throw new IllegalArgumentException("Invalid rover position or null rover.");
        }
    }

    @Override
    public void addObstacle(Obstacle obstacle) {
        if (obstacle != null && isEmpty(obstacle.getPosition())) {
            obstacles.put(obstacle.getPosition(), obstacle);
        } else {
            throw new IllegalArgumentException("Invalid obstacle position or null obstacle.");
        }
    }

    @Override
    public boolean isEmpty(Coordinate coordinate) {
        return !isObstacle(coordinate) && !isRover(coordinate);
    }

    @Override
    public String toString() {
        return "PlanetGrid{" +
                "name='" + getName() + '\'' +
                "width=" + width +
                ", height=" + height +
                ", deployedRovers=" + deployedRovers +
                '}';
    }


}
