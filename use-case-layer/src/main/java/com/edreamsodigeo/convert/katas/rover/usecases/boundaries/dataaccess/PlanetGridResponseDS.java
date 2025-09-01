package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess;

import java.util.List;

public interface PlanetGridResponseDS {

    int getWidth();
    int getHeight();
    String getPlanetName();
    List<RoverResponseDS> getDeployedRovers();
    List<ObstacleResponseDS> getObstacles();

}
