package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess.PlanetGridRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryPlanetGridRepository implements PlanetGridRepository {

    private final Map<String, PlanetGridDataMapper> planetGridMap = new HashMap<>();

    @Override
    public boolean existsByName(String name) {
        return planetGridMap.containsKey(name);
    }

    @Override
    public void save(PlanetGridDataMapper planetGridDataMapper) {
        planetGridMap.put(planetGridDataMapper.getPlanetName(), planetGridDataMapper);
    }

    @Override
    public PlanetGridDataMapper findByName(String name) {
        return planetGridMap.get(name);
    }

    @Override
    public PlanetGridDataMapper findByRoverName(String roverName) {
        for (PlanetGridDataMapper planetGrid : planetGridMap.values()) {
            if (planetGrid.getDeployedRovers().stream()
                    .anyMatch(rover -> rover.getName().equals(roverName))) {
                return planetGrid;
            }
        }
        return null;
    }
}
