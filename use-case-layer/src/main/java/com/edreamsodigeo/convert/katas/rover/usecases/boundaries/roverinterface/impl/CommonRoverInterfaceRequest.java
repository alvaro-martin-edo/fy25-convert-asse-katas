package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommand;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;

public class CommonRoverInterfaceRequest implements RoverInterfaceRequest {

    private final String roverName;
    private final RoverCommand command;

    public CommonRoverInterfaceRequest(String roverName, RoverCommand command) {
        this.roverName = roverName;
        this.command = command;
    }

    @Override
    public String getRoverName() {
        return roverName;
    }

    @Override
    public String getCommand() {
        return command.name();
    }
}
