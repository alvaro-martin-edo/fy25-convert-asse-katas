package com.edreamsodigeo.convert.katas.rover.entities.factory;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;

public interface CoordinateFactory {
    Coordinate create(int horizontalPosition, int verticalPosition);
}
