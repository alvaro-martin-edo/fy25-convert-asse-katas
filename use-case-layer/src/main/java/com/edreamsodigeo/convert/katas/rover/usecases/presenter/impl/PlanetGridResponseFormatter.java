package com.edreamsodigeo.convert.katas.rover.usecases.presenter.impl;

import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.PlanetGridResponseException;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.PlanetGridResponsePresenter;

public class PlanetGridResponseFormatter implements PlanetGridResponsePresenter {

    @Override
    public PlanetGridResponseModel prepareSuccessResponse(
            PlanetGridResponseModel planetGridResponseModel) {
        planetGridResponseModel.setSize("Grid Size: " + planetGridResponseModel.getSize());
        planetGridResponseModel.setObstacles("Obstacles: " + planetGridResponseModel.getObstacles());
        return planetGridResponseModel;
    }

    @Override
    public PlanetGridResponseModel prepareErrorResponse(String errorMessage) {
        throw new PlanetGridResponseException(errorMessage);
    }
}
