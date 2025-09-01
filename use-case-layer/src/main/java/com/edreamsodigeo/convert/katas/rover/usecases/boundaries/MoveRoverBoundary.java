package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;

public interface MoveRoverBoundary {
    RoverResponseModel move(MoveRoverRequestModel moveRoverRequestModel);
}
