package com.edreamsodigeo.convert.katas.rover.entities;

public interface Coordinate {

    int getHorizontalPosition();

    int getVerticalPosition();

    boolean isValid(PlanetGrid grid);

}
