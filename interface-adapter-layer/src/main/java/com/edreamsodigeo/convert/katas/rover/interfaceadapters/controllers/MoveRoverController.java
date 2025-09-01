package com.edreamsodigeo.convert.katas.rover.interfaceadapters.controllers;

import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.MoveRoverBoundary;

public class MoveRoverController {

    private MoveRoverBoundary moveRoverBoundary;

    // /mission-control/rovers/xxxx/move
    public RoverResponseModel move(MoveRoverRequestModel moveRoverRequestModel) {
        return moveRoverBoundary.move(moveRoverRequestModel);
    }



}
