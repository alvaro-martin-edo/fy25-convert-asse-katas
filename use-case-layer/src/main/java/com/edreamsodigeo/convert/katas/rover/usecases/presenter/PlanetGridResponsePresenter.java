package com.edreamsodigeo.convert.katas.rover.usecases.presenter;

import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;

public interface PlanetGridResponsePresenter {

    PlanetGridResponseModel prepareSuccessResponse(PlanetGridResponseModel planetGridResponseModel);

    PlanetGridResponseModel prepareErrorResponse(String errorMessage);
}
