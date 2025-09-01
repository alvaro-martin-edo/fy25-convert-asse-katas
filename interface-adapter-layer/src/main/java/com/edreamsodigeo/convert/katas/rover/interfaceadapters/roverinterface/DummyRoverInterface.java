package com.edreamsodigeo.convert.katas.rover.interfaceadapters.roverinterface;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.ObstacleDetectedException;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverStatus;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl.CommonRoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;

public class DummyRoverInterface implements RoverInterfaceGateway {

    private final PlanetGrid planetGrid;
    private final Rover rover;
    private final CoordinateFactory coordinateFactory;

    public DummyRoverInterface(CoordinateFactory coordinateFactory, PlanetGrid planetGrid, Rover rover) {
        this.planetGrid = planetGrid;
        this.rover = rover;
        this.coordinateFactory = coordinateFactory;
    }

    @Override
    public RoverInterfaceResponse command(RoverInterfaceRequest roverInterfaceRequest) throws ObstacleDetectedException {
        Coordinate newCoordinate = calculateNewCoordinate(rover, roverInterfaceRequest);
        Direction newDirection = calculateNewDirection(rover, roverInterfaceRequest);

        if (!planetGrid.isValid(newCoordinate)) {
            newCoordinate = coordinateFactory.create(0,0);
            newDirection = Direction.NORTH;
        }
        if (planetGrid.isObstacle(newCoordinate)) {
            throw new ObstacleDetectedException("Obstacle detected in destination.");
        }
        if (planetGrid.isRover(newCoordinate)) {
            throw new ObstacleDetectedException("Rover detected in destination.");
        }

        return new CommonRoverInterfaceResponse(
                rover.getName(),
                newDirection.name(),
                newCoordinate.getHorizontalPosition(),
                newCoordinate.getVerticalPosition(),
                RoverStatus.ACTIVE
        );
    }

    private Direction calculateNewDirection(Rover rover, RoverInterfaceRequest roverInterfaceRequest) {
        switch (roverInterfaceRequest.getCommand()) {
            case "turn_left":
                if (rover.getDirection() == Direction.NORTH) {
                    return Direction.WEST;
                } else if (rover.getDirection() == Direction.WEST) {
                    return Direction.SOUTH;
                } else if (rover.getDirection() == Direction.SOUTH) {
                    return Direction.EAST;
                } else if (rover.getDirection() == Direction.EAST) {
                    return Direction.NORTH;
                }
            case "turn_right":
                if (rover.getDirection() == Direction.NORTH) {
                    return Direction.EAST;
                } else if (rover.getDirection() == Direction.EAST) {
                    return Direction.SOUTH;
                } else if (rover.getDirection() == Direction.SOUTH) {
                    return Direction.WEST;
                } else if (rover.getDirection() == Direction.WEST) {
                    return Direction.NORTH;
                }
            default:
                return rover.getDirection();
        }
    }

    private Coordinate calculateNewCoordinate(Rover rover, RoverInterfaceRequest request) {
        switch (request.getCommand()) {
            case "move":
                int x = rover.getHorizontalPosition();
                int y = rover.getVerticalPosition();
                switch (rover.getDirection()) {
                    case NORTH:
                        y += 1;
                        break;
                    case SOUTH:
                        y -= 1;
                        break;
                    case EAST:
                        x += 1;
                        break;
                    case WEST:
                        x -= 1;
                        break;
                }
                return coordinateFactory.create(x, y);
            case "turn_left":
            case "turn_right":
                return rover.getPosition();
            default:
                throw new IllegalArgumentException("Unknown Command: " + request.getCommand());
        }
    }

}
