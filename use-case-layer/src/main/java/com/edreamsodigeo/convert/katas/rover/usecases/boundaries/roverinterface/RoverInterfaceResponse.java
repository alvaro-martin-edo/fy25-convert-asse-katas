package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface;

public interface RoverInterfaceResponse {

    String getRoverName();

    String getDirection();

    int getHorizontalPosition();

    int getVerticalPosition();

    RoverStatus getStatus();

}
