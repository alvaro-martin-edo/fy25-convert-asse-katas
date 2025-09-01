package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;

public interface RoverStatusDSGateway {
    void updateStatus(RoverRequestDS roverRequestDS);
}
