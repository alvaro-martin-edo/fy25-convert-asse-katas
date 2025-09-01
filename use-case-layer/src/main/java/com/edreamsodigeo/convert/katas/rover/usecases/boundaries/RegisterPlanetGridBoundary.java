package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.model.request.RegisterPlanetGridRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;

public interface RegisterPlanetGridBoundary {
    PlanetGridResponseModel register(RegisterPlanetGridRequestModel registerPlanetGridRequestModel);
}
