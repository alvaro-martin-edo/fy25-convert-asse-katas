package com.edreamsodigeo.convert.katas.rover.entities;

import java.util.Objects;

public enum Direction {

    EAST("L"),
    WEST("R"),
    NORTH("N"),
    SOUTH("S");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Direction fromValue(String value) {
        for (Direction direction : values()) {
            if (Objects.equals(direction.value, value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction value: " + value);
    }


}
