package com.edreamsodigeo.convert.katas.rover.entities.factory.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.entities.impl.StandardPlanetGrid;

import java.util.Map;

public class StandardPlanedGridFactory implements PlanetGridFactory {

    @Override
    public StandardPlanetGrid create(int width, int height, String name) {
        return new StandardPlanetGrid(width, height, name);
    }

    @Override
    public StandardPlanetGrid create(int width, int height, String planetName, Map<Coordinate, Rover> deployedRovers, Map<Coordinate, Obstacle> obstacles) {
        return new StandardPlanetGrid(width, height, planetName, deployedRovers, obstacles);
    }

}
