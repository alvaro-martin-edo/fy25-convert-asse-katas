package com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl.RoverDataMapper;

public interface RoverRepository {
    boolean existsByName(String name);

    void save(RoverDataMapper roverDataMapper);

    RoverDataMapper findByName(String name);
}
