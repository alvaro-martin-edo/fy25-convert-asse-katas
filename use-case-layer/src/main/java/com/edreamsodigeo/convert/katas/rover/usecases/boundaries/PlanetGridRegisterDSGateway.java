package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;

public interface PlanetGridRegisterDSGateway {

    boolean existsByName(String name);

    void save(PlanetGridRequestDS planetGridRequestDS);

    PlanetGridResponseDS findByPlanetName(String name);

    PlanetGridResponseDS findByRoverName(String roverName);

}
