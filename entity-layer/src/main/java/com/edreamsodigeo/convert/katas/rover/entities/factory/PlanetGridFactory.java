package com.edreamsodigeo.convert.katas.rover.entities.factory;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;

import java.util.Map;

public interface PlanetGridFactory {
    PlanetGrid create(int width, int height, String name);

    PlanetGrid create(int width, int height, String planetName, Map<Coordinate, Rover> deployedRovers, Map<Coordinate, Obstacle> obstacles);
}
