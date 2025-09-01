package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

import java.util.List;
import java.util.ArrayList;

public class PlanetGridDataMapper {

    private String planetName;
    private int width;
    private int height;
    private List<RoverDataMapper> deployedRovers;
    private List<ObstacleDataMapper> obstacles;

    public PlanetGridDataMapper(String planetName, int width, int height) {
        this.planetName = planetName;
        this.width = width;
        this.height = height;
    }

    public PlanetGridDataMapper(String planetName, int width, int height, List<RoverDataMapper> deployedRovers, List<ObstacleDataMapper> obstacles) {
        this.planetName = planetName;
        this.width = width;
        this.height = height;
        this.deployedRovers = deployedRovers != null ? deployedRovers : new ArrayList<>();
        this.obstacles = obstacles != null ? obstacles : new ArrayList<>();
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<RoverDataMapper> getDeployedRovers() {
        return deployedRovers;
    }

    public List<ObstacleDataMapper> getObstacles() {
        return obstacles;
    }
}
