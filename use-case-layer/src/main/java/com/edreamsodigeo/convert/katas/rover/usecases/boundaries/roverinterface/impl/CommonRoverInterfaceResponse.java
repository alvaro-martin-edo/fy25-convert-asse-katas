package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverStatus;

public class CommonRoverInterfaceResponse implements RoverInterfaceResponse {

    private final String roverName;
    private final String direction;
    private final int horizontalPosition;
    private final int verticalPosition;
    private final RoverStatus status;

    public CommonRoverInterfaceResponse(String roverName, String direction, int horizontalPosition, int verticalPosition, RoverStatus status) {
        this.roverName = roverName;
        this.direction = direction;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.status = status;
    }

    @Override
    public String getRoverName() {
        return roverName;
    }

    @Override
    public String getDirection() {
        return direction;
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
    public RoverStatus getStatus() {
        return status;
    }
}
