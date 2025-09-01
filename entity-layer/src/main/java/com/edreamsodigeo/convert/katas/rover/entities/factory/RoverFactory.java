package com.edreamsodigeo.convert.katas.rover.entities.factory;

import com.edreamsodigeo.convert.katas.rover.entities.Rover;

public interface RoverFactory {
    Rover create(String name, String direction, int horizontalPosition, int verticalPosition, boolean active);
}
