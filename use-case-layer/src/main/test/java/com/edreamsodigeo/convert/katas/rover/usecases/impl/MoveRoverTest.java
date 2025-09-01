package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.ObstacleFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverInterfaceGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverStatusDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.impl.MoveRover;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessorFactory;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceResponse;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl.BasicRoverCommandProcessor;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class MoveRoverTest {

    @Mock private RoverStatusDSGateway roverStatusDSGateway;
    @Mock private RoverRegisterDSGateway roverRegisterDSGateway;
    @Mock private RoverInterfaceGateway roverInterfaceGateway;
    @Mock private RoverFactory roverFactory;
    @Mock private PlanetGridFactory planetGridFactory;
    @Mock private CoordinateFactory coordinateFactory;
    @Mock private ObstacleFactory obstacleFactory;
    @Mock private RoverResponsePresenter roverResponsePresenter;
    @Mock private PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    @Mock private RoverCommandProcessorFactory roverCommandProcessorFactory;
    @Mock private RoverResponseDS roverResponseDS;
    @Mock private RoverInterfaceResponse interfaceResponse;
    @Mock private Rover rover;
    @Mock private PlanetGridResponseDS planetGridResponseDS;

    private MoveRover moveRover;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        moveRover = new MoveRover(
                roverStatusDSGateway,
                roverInterfaceGateway,
                roverRegisterDSGateway,
                roverFactory,
                planetGridFactory,
                coordinateFactory,
                obstacleFactory,
                roverResponsePresenter,
                planetGridRegisterDSGateway,
                roverCommandProcessorFactory
        );
    }

    @Test
    public void givenARoverMovementRequestWhenWeCallToDeployItThenTheRoverIsMovedSuccessfully() {
        MoveRoverRequestModel request = new MoveRoverRequestModel("Earth", "Rover1", "MMRMMLM");
        givenASuccessfullyRoverMovement(request);

        RoverResponseModel response = moveRover.move(request);

        assertNotNull(response);
        assertEquals(response.getName(), "Rover1");
        assertEquals(response.getLocation(), "2:3:N");
        assertEquals(response.getLastCommand(), "MMRMMLM");
        verify(roverStatusDSGateway).updateStatus(any());
    }

    private void givenASuccessfullyRoverMovement(MoveRoverRequestModel request) {
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(true);
        when(roverRegisterDSGateway.findByName("Rover1")).thenReturn(roverResponseDS);
        when(roverResponseDS.getName()).thenReturn(request.getRoverName());
        when(roverResponseDS.getDirection()).thenReturn("N");
        when(roverResponseDS.getHorizontalPosition()).thenReturn(0);
        when(roverResponseDS.getVerticalPosition()).thenReturn(0);
        when(roverResponseDS.isActive()).thenReturn(true);
        when(roverFactory.create(request.getRoverName(), "N", 0, 0, true)).thenReturn(rover);
        when(rover.getName()).thenReturn(request.getRoverName());
        when(rover.getDirection()).thenReturn(Direction.NORTH);
        when(rover.getHorizontalPosition()).thenReturn(0);
        when(rover.getVerticalPosition()).thenReturn(0);
        when(rover.isActive()).thenReturn(true);

        when(planetGridRegisterDSGateway.findByRoverName(request.getRoverName())).thenReturn(planetGridResponseDS);
        when(planetGridResponseDS.getWidth()).thenReturn(5);
        when(planetGridResponseDS.getHeight()).thenReturn(5);
        when(planetGridResponseDS.getPlanetName()).thenReturn("Earth");
        when(planetGridResponseDS.getDeployedRovers()).thenReturn(Collections.singletonList(roverResponseDS));
        when(planetGridResponseDS.getObstacles()).thenReturn(Collections.emptyList());

        when(planetGridFactory.create(any(), any(), any(), any(), any())).thenReturn(null);

        when(roverCommandProcessorFactory.getProcessor(any())).thenReturn(new BasicRoverCommandProcessor());
        when(roverInterfaceGateway.command(any(RoverInterfaceRequest.class))).thenReturn(interfaceResponse);

        when(interfaceResponse.getHorizontalPosition()).thenReturn(2);
        when(interfaceResponse.getVerticalPosition()).thenReturn(3);
        when(interfaceResponse.getDirection()).thenReturn("N");
        when(interfaceResponse.getRoverName()).thenReturn(request.getRoverName());

        when(roverResponsePresenter.prepareSuccessResponse(any(RoverResponseModel.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }
}