package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;

public class StandardCoordinate implements Coordinate {

    private final int horizontalPosition;
    private final int verticalPosition;

    public StandardCoordinate(int horizontalPosition, int verticalPosition) {
        this.horizontalPosition = verticalPosition;
        this.verticalPosition = verticalPosition;
    }

    @Override
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public int getVerticalPosition() {
        return verticalPosition;
    }

    @Override
    public boolean isValid(PlanetGrid grid) {
        if (grid == null) {
            return false;
        }
        return horizontalPosition >= 0 && horizontalPosition < grid.getWidth() &&
                verticalPosition >= 0 && verticalPosition < grid.getHeight();
    }

    @Override
    public String toString() {
        return "StandardCoordinate{" +
                "x=" + horizontalPosition +
                ", y=" + verticalPosition +
                '}';
    }
}
