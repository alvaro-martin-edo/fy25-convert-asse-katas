package com.edreamsodigeo.convert.katas.rover.entities;

public enum ObstacleType {
    MOUNTAIN,
    CRATER,
    WATER,
    ICE,
    CLIFF,
    UNKNOWN;

    public static ObstacleType fromString(String type) {
        try {
            return ObstacleType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
