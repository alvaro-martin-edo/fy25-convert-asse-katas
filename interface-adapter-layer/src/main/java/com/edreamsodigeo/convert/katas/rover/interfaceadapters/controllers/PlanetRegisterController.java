package com.edreamsodigeo.convert.katas.rover.interfaceadapters.controllers;

import com.edreamsodigeo.convert.katas.rover.model.request.RegisterPlanetGridRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RegisterPlanetGridBoundary;

public class PlanetRegisterController {

    // /astronauts/planets/register
    private RegisterPlanetGridBoundary registerPlanetGridBoundary;

    // @PostMapping("/register")
    public PlanetGridResponseModel register(RegisterPlanetGridRequestModel registerPlanetGridRequestModel) {
         return registerPlanetGridBoundary.register(registerPlanetGridRequestModel);
    }

}
