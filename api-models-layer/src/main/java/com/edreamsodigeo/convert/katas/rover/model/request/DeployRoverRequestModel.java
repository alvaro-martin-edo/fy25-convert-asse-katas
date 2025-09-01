package com.edreamsodigeo.convert.katas.rover.model.request;

public class DeployRoverRequestModel {

    private final String roverName;
    private final String direction;
    private final String planetName;
    private final int horizontalPosition;
    private final int verticalPosition;

    public DeployRoverRequestModel(String roverName, String direction, String planetName, int horizontalPosition, int verticalPosition) {
        this.roverName = roverName;
        this.direction = direction;
        this.planetName = planetName;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public String getRoverName() {
        return roverName;
    }

    public String getDirection() {
        return direction;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public String getPlanetName() {
        return planetName;
    }
}
