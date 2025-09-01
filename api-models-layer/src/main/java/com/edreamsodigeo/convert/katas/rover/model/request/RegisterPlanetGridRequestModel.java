package com.edreamsodigeo.convert.katas.rover.model.request;

public class RegisterPlanetGridRequestModel {

    private final String planetName;
    private final int horizontalSize;
    private final int verticalSize;

    public RegisterPlanetGridRequestModel(String planetName, int horizontalSize, int verticalSize) {
        this.planetName = planetName;
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }
}
