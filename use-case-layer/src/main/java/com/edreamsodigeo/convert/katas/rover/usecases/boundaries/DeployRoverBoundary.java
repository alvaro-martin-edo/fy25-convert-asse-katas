package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;

public interface DeployRoverBoundary {
    RoverResponseModel deploy(DeployRoverRequestModel request);
}
