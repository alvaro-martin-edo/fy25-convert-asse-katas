package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess.RoverRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryRoverRepository implements RoverRepository {

    private final Map<String, RoverDataMapper> roverMap = new HashMap<>();

    @Override
    public boolean existsByName(String name) {
        return roverMap.containsKey(name);
    }

    @Override
    public void save(RoverDataMapper roverDataMapper) {
        roverMap.put(roverDataMapper.getName(), roverDataMapper);
    }

    @Override
    public RoverDataMapper findByName(String name) {
        return roverMap.get(name);
    }


}
