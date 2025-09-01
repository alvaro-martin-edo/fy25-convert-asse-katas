package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.impl;

import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.RegisterPlanetGridRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RegisterPlanetGridBoundary;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.PlanetGridRequestDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.PlanetGridResponsePresenter;

import java.time.LocalDateTime;

public class RegisterPlanetGrid implements RegisterPlanetGridBoundary {

    private final PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    private final PlanetGridFactory planetGridFactory;
    private final PlanetGridResponsePresenter planetGridResponsePresenter;

    public RegisterPlanetGrid(PlanetGridRegisterDSGateway planetGridRegisterDSGateway,
                                 PlanetGridFactory planetGridFactory,
                                 PlanetGridResponsePresenter planetGridResponsePresenter) {
        this.planetGridRegisterDSGateway = planetGridRegisterDSGateway;
        this.planetGridFactory = planetGridFactory;
        this.planetGridResponsePresenter = planetGridResponsePresenter;
    }

    @Override
    public PlanetGridResponseModel register(RegisterPlanetGridRequestModel registerPlanetGridRequestModel) {

        if (planetGridRegisterDSGateway.existsByName(registerPlanetGridRequestModel.getPlanetName())) {
            throw new IllegalArgumentException("Planet grid already exists: " + registerPlanetGridRequestModel.getPlanetName());
        }

        PlanetGrid planetGrid = planetGridFactory.create(
                registerPlanetGridRequestModel.getHorizontalSize(),
                registerPlanetGridRequestModel.getVerticalSize(),
                registerPlanetGridRequestModel.getPlanetName()
        );

        LocalDateTime now = LocalDateTime.now();

        PlanetGridRequestDS planetGridRequestDS = new PlanetGridRequestDSImpl(
                planetGrid.getName(),
                planetGrid.getWidth(),
                planetGrid.getHeight(),
                now,
                now);

        planetGridRegisterDSGateway.save(planetGridRequestDS);

        PlanetGridResponseModel planetGridResponseModel = new PlanetGridResponseModel(
                planetGrid.getName(),
                formatSize(planetGrid.getWidth(), planetGrid.getHeight()),
                ""
        );
        return planetGridResponsePresenter.prepareSuccessResponse(planetGridResponseModel);

    }

    private String formatSize(Integer width, Integer height) {
        return String.format("%dx%d", width, height);
    }

}
