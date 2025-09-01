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
import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.MoveRoverBoundary;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverStatusDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.ObstacleResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.RoverRequestDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.ObstacleDetectedException;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessorFactory;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverStatus;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl.CommonRoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class MoveRover implements MoveRoverBoundary {

    private final RoverStatusDSGateway roverStatusDSGateway;
    private final RoverRegisterDSGateway roverRegisterDSGateway;
    private final RoverInterfaceGateway roverInterfaceGateway;
    private final RoverFactory roverFactory;
    private final PlanetGridFactory planetGridFactory;
    private final CoordinateFactory coordinateFactory;
    private final ObstacleFactory obstacleFactory;
    private final RoverResponsePresenter roverResponsePresenter;
    private final PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    private final RoverCommandProcessorFactory roverCommandProcessorFactory;


    public MoveRover(RoverStatusDSGateway roverStatusDSGateway,
                     RoverInterfaceGateway roverInterfaceGateway,
                     RoverRegisterDSGateway roverRegisterDSGateway,
                     RoverFactory roverFactory,
                     PlanetGridFactory planetGridFactory,
                     CoordinateFactory coordinateFactory,
                     ObstacleFactory obstacleFactory,
                     RoverResponsePresenter roverResponsePresenter,
                     PlanetGridRegisterDSGateway planetGridRegisterDSGateway,
                     RoverCommandProcessorFactory roverCommandProcessorFactory) {
        this.roverInterfaceGateway = roverInterfaceGateway;
        this.roverStatusDSGateway = roverStatusDSGateway;
        this.roverRegisterDSGateway = roverRegisterDSGateway;
        this.roverFactory = roverFactory;
        this.planetGridFactory = planetGridFactory;
        this.coordinateFactory = coordinateFactory;
        this.obstacleFactory = obstacleFactory;
        this.roverResponsePresenter = roverResponsePresenter;
        this.planetGridRegisterDSGateway = planetGridRegisterDSGateway;
        this.roverCommandProcessorFactory = roverCommandProcessorFactory;
    }

    @Override
    public RoverResponseModel move(MoveRoverRequestModel moveRoverRequestModel) {

        if (!roverRegisterDSGateway.existsByName(moveRoverRequestModel.getRoverName())) {
            throw new IllegalArgumentException("Rover " + moveRoverRequestModel.getRoverName() + " is not registered.");
        }

        RoverResponseDS roverResponseDS = roverRegisterDSGateway.findByName(moveRoverRequestModel.getRoverName());
        PlanetGridResponseDS planetGridResponseDS = planetGridRegisterDSGateway.findByRoverName(moveRoverRequestModel.getRoverName());

        if (planetGridResponseDS == null) {
            throw new IllegalArgumentException("Rover " + moveRoverRequestModel.getRoverName() + " is not registered in any planet grid.");
        }
        Rover rover = roverFactory.create(roverResponseDS.getName(), roverResponseDS.getDirection(), roverResponseDS.getHorizontalPosition(), roverResponseDS.getVerticalPosition(), roverResponseDS.isActive());


        PlanetGrid planetGrid = planetGridFactory.create(
                planetGridResponseDS.getWidth(),
                planetGridResponseDS.getHeight(),
                planetGridResponseDS.getPlanetName(),
                mapDeployedRovers(planetGridResponseDS.getDeployedRovers()),
                mapObstacles(planetGridResponseDS.getObstacles())
        );

        if (!rover.isActive()) {
            throw new IllegalArgumentException("Rover " + rover.getName() + " is not active, check mission control.");
        }

        RoverInterfaceResponse roverInterfaceResponse = executeCommand(moveRoverRequestModel, planetGrid, rover);
        updateRoverStatus(roverInterfaceResponse);

        RoverResponseModel roverResponseModel = new RoverResponseModel(
                rover.getName(),
                formatLocation(rover.getDirection(), roverInterfaceResponse.getHorizontalPosition(), roverInterfaceResponse.getVerticalPosition()),
                moveRoverRequestModel.getCommand(),
                "",
                true
        );

        return roverResponsePresenter.prepareSuccessResponse(roverResponseModel);
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

    private String formatLocation(Direction direction, Integer horizontalPosition, Integer verticalPosition) {
        return String.format("%d:%d:%s", horizontalPosition, verticalPosition, direction.getValue());
    }

    private RoverInterfaceResponse executeCommand(MoveRoverRequestModel moveRoverRequestModel,
                                                  PlanetGrid planetGrid,
                                                  Rover rover) {

        RoverInterfaceResponse roverInterfaceResponse = null;
        List<RoverInterfaceRequest> roverInterfaceRequests = roverCommandProcessorFactory.getProcessor(moveRoverRequestModel.getCommand())
                .generateRoverInterfaceRequests(moveRoverRequestModel.getPlanetName(), moveRoverRequestModel.getRoverName(), moveRoverRequestModel.getCommand());

        for (RoverInterfaceRequest roverInterfaceRequest : roverInterfaceRequests) {
            try {
                roverInterfaceResponse = roverInterfaceGateway.command(roverInterfaceRequest);
            } catch (ObstacleDetectedException e) {
                if (roverInterfaceResponse != null) {
                    roverInterfaceResponse = new CommonRoverInterfaceResponse(
                            roverInterfaceResponse.getRoverName(),
                            roverInterfaceResponse.getDirection(),
                            roverInterfaceResponse.getHorizontalPosition(),
                            roverInterfaceResponse.getVerticalPosition(),
                            RoverStatus.OBSTACLE_DETECTED
                    );
                } else {
                    roverInterfaceResponse = new CommonRoverInterfaceResponse(
                            rover.getName(),
                            rover.getDirection().getValue(),
                            rover.getHorizontalPosition(),
                            rover.getVerticalPosition(),
                            RoverStatus.OBSTACLE_DETECTED
                    );
                }

            }
        }
        return roverInterfaceResponse;
    }

    private void updateRoverStatus(RoverInterfaceResponse roverInterfaceResponse) {
        RoverRequestDS roverRequestDS = new RoverRequestDSImpl(
                roverInterfaceResponse.getRoverName(),
                roverInterfaceResponse.getDirection(),
                roverInterfaceResponse.getHorizontalPosition(),
                roverInterfaceResponse.getVerticalPosition(),
                LocalDateTime.now()
        );
        roverStatusDSGateway.updateStatus(roverRequestDS);
    }

}
