package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.ObstacleResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;

import java.util.ArrayList;
import java.util.List;

public class PlanetGridResponseDSImpl implements PlanetGridResponseDS {

    private final int width;
    private final int height;
    private final String planetName;

    public PlanetGridResponseDSImpl(int width, int height, String planetName) {
        this.width = width;
        this.height = height;
        this.planetName = planetName;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getPlanetName() {
        return planetName;
    }
    @Override
    public List<RoverResponseDS> getDeployedRovers() {
        return new ArrayList<>();
    }

    @Override
    public List<ObstacleResponseDS> getObstacles() {
        return new ArrayList<>();
    }

}
