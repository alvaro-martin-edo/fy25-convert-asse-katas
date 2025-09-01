package com.edreamsodigeo.convert.katas.rover.model.request;

public class MoveRoverRequestModel {
    private String planetName;
    private String roverName;
    private String command;

    public MoveRoverRequestModel(String planetName, String roverName, String command) {
        this.roverName = roverName;
        this.command = command;
        this.planetName = planetName;
    }

    public String getRoverName() {
        return roverName;
    }
    public String getCommand() {
        return command;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }


}
