package com.edreamsodigeo.convert.katas.rover.interfaceadapters.controllers;

import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.DeployRoverBoundary;

public class DeployRoverController {

    // /mission-control/rovers/xxxx/deploy
    private DeployRoverBoundary deployRouterBoundary;

    //@PostMapping("/user")
    RoverResponseModel deploy(DeployRoverRequestModel deployRoverRequestModel) {
        return deployRouterBoundary.deploy(deployRoverRequestModel);
    }

}
