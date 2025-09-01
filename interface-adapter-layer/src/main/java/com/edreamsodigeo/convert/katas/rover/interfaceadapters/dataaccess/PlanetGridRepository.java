package com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl.PlanetGridDataMapper;

public interface PlanetGridRepository {

    boolean existsByName(String name);

    void save(PlanetGridDataMapper planetGridDataMapper);

    PlanetGridDataMapper findByName(String name);

    PlanetGridDataMapper findByRoverName(String roverName);
}
