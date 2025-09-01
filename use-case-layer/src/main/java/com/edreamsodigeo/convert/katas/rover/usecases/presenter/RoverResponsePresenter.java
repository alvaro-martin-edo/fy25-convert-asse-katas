package com.edreamsodigeo.convert.katas.rover.usecases.presenter;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;

public interface RoverResponsePresenter {

    RoverResponseModel prepareSuccessResponse(RoverResponseModel roverResponseModel);

    RoverResponseModel prepareErrorResponse(String errorMessage);

}
