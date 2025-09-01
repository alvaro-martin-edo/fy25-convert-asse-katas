package com.edreamsodigeo.convert.katas.rover.usecases.presenter.impl;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.RoverResponseException;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

public class RoverResponseFormatter implements RoverResponsePresenter {

    @Override
    public RoverResponseModel prepareSuccessResponse(RoverResponseModel response) {
        response.setLastCommand("Command: " + response.getLastCommand());
        response.setLocation("Location: " + response.getLocation());
        return response;
    }

    @Override
    public RoverResponseModel prepareErrorResponse(String errorMessage) {
        throw new RoverResponseException(errorMessage);
    }

}
