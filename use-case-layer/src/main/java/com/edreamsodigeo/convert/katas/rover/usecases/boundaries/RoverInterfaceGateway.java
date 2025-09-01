package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.usecases.exception.ObstacleDetectedException;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;

public interface RoverInterfaceGateway {
    RoverInterfaceResponse command(RoverInterfaceRequest roverInterfaceRequest) throws ObstacleDetectedException;
}
