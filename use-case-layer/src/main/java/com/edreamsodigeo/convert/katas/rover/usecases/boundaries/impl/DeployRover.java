package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Obstacle;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.ObstacleFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.DeployRoverBoundary;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.ObstacleResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.RoverRequestDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeployRover implements DeployRoverBoundary {

    private final RoverRegisterDSGateway roverRegisterDSGateway;
    private final PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    private final RoverFactory roverFactory;
    private final PlanetGridFactory planetGridFactory;
    private final RoverResponsePresenter roverResponsePresenter;
    private final CoordinateFactory coordinateFactory;
    private final ObstacleFactory obstacleFactory;

    public DeployRover(RoverRegisterDSGateway roverRegisterDSGateway, PlanetGridRegisterDSGateway planetGridRegisterDSGateway,
                       RoverFactory roverFactory, RoverResponsePresenter roverResponsePresenter,
                       PlanetGridFactory planetGridFactory, ObstacleFactory obstacleFactory, CoordinateFactory coordinateFactory) {
        this.roverRegisterDSGateway = roverRegisterDSGateway;
        this.roverFactory = roverFactory;
        this.roverResponsePresenter = roverResponsePresenter;
        this.planetGridRegisterDSGateway = planetGridRegisterDSGateway;
        this.planetGridFactory = planetGridFactory;
        this.coordinateFactory = coordinateFactory;
        this.obstacleFactory = obstacleFactory;
    }

    @Override
    public RoverResponseModel deploy(DeployRoverRequestModel request) {
        if (roverRegisterDSGateway.existsByName(request.getRoverName())) {
            throw new IllegalArgumentException("Rover with name " + request.getRoverName() + " already exists.");
        }
        PlanetGridResponseDS planetGridResponseDS = planetGridRegisterDSGateway.findByRoverName(request.getPlanetName());
        Rover rover = roverFactory.create(request.getRoverName(), request.getDirection(), request.getHorizontalPosition(), request.getVerticalPosition(), true);
        PlanetGrid planetGrid = planetGridFactory.create(
                planetGridResponseDS.getWidth(),
                planetGridResponseDS.getHeight(),
                planetGridResponseDS.getPlanetName(),
                mapDeployedRovers(planetGridResponseDS.getDeployedRovers()),
                mapObstacles(planetGridResponseDS.getObstacles())
        );
        Coordinate desiredCoordinate = coordinateFactory.create(rover.getHorizontalPosition(), rover.getVerticalPosition());
        if (!planetGrid.isValid(desiredCoordinate) || !planetGrid.isEmpty(desiredCoordinate)) {
            throw new IllegalArgumentException("Invalid position for rover deployment: " + rover.getHorizontalPosition() + ", " + rover.getVerticalPosition());
        }

        LocalDateTime now = LocalDateTime.now();
        RoverRequestDS roverDSModel = new RoverRequestDSImpl(
                rover.getName(),
                rover.getDirection().getValue(),
                rover.getHorizontalPosition(),
                rover.getVerticalPosition(),
                now,
                now,
                true
        );

        roverRegisterDSGateway.save(roverDSModel);

        RoverResponseModel roverResponseModel = new RoverResponseModel(rover.getName(),
                formatLocation(rover.getDirection(), rover.getHorizontalPosition(), rover.getVerticalPosition()),
                "Deployed", "", true);
        return roverResponsePresenter.prepareSuccessResponse(roverResponseModel);
    }

    private String formatLocation(Direction direction, int horizontalPosition, int verticalPosition) {
        return String.format("%s:%d:%d", direction.getValue(), horizontalPosition, verticalPosition);
    }

    private Map<Coordinate, Obstacle> mapObstacles(List<ObstacleResponseDS> obstacles) {
        return obstacles.stream()
                .collect(Collectors.toMap(
                        obstacleResponseDS -> coordinateFactory.create(obstacleResponseDS.getHorizontalPosition(), obstacleResponseDS.getVerticalPosition()),
                        obstacleResponseDS -> obstacleFactory.create(obstacleResponseDS.getHorizontalPosition(), obstacleResponseDS.getVerticalPosition(), obstacleResponseDS.getObstacleType())
                ));
    }

    private Map<Coordinate, Rover> mapDeployedRovers(List<RoverResponseDS> deployedRovers) {
        return deployedRovers.stream()
                .collect(Collectors.toMap(
                        roverResponseDS -> coordinateFactory.create(roverResponseDS.getHorizontalPosition(), roverResponseDS.getVerticalPosition()),
                        roverResponseDS -> roverFactory.create(roverResponseDS.getName(), roverResponseDS.getDirection(), roverResponseDS.getHorizontalPosition(), roverResponseDS.getVerticalPosition(), roverResponseDS.isActive())
                ));
    }
}

