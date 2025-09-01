package com.edreamsodigeo.convert.katas.rover.usecases.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import com.edreamsodigeo.convert.katas.rover.entities.Rover;
import com.edreamsodigeo.convert.katas.rover.entities.factory.CoordinateFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.ObstacleFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.entities.factory.RoverFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.DeployRoverRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.impl.DeployRover;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.RoverResponsePresenter;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class DeployRoverTest {

    private DeployRover deployRover;
    @Mock private RoverRegisterDSGateway roverRegisterDSGateway;
    @Mock private PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    @Mock private RoverFactory roverFactory;
    @Mock private PlanetGridFactory planetGridFactory;
    @Mock private CoordinateFactory coordinateFactory;
    @Mock private ObstacleFactory obstacleFactory;
    @Mock private RoverResponsePresenter roverResponsePresenter;
    @Mock private Rover rover;
    @Mock private PlanetGridResponseDS planetGridResponseDS;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        deployRover = new DeployRover(
                roverRegisterDSGateway,
                planetGridRegisterDSGateway,
                roverFactory,
                roverResponsePresenter,
                planetGridFactory,
                obstacleFactory,
                coordinateFactory
        );
    }

    @Test
    public void givenARoverDeploymentRequestWhenWeCallToDeployItThenTheRoverIsDeployedSuccessfully() {
        DeployRoverRequestModel request = new DeployRoverRequestModel("Rover1", "N", "Earth", 0, 0);
        givenASuccessfullyRoverRegistration(request);
        ArgumentCaptor<RoverRequestDS> captor = ArgumentCaptor.forClass(RoverRequestDS.class);

        RoverResponseModel response = deployRover.deploy(request);

        assertNotNull(response);
        assertEquals(response.getName(), "Rover1");
        assertEquals(response.getLocation(), "N:0:0");
        assertTrue(response.isSuccess());
        verify(roverRegisterDSGateway).save(any(RoverRequestDS.class));
        verify(roverRegisterDSGateway).save(captor.capture());
        RoverRequestDS savedData = captor.getValue();
        assertEquals(savedData.getName(), "Rover1");
        assertEquals(savedData.getDirection(), "N");
        assertEquals(savedData.getHorizontalPosition(), 0);
        assertEquals(savedData.getVerticalPosition(), 0);
        assertTrue(savedData.isActive());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenAnExistingRoverDeploymentRequestWhenWeCallToDeployItThenAnExceptionIsThrown() {
        DeployRoverRequestModel request = new DeployRoverRequestModel("Rover1", "E", "Venus", 0, 0);
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(true);
        deployRover.deploy(request);
    }

    private void givenASuccessfullyRoverRegistration(DeployRoverRequestModel request) {
        when(roverRegisterDSGateway.existsByName(request.getRoverName())).thenReturn(false);
        when(roverFactory.create(request.getRoverName(), request.getDirection(), request.getHorizontalPosition(), request.getVerticalPosition(), true)).thenReturn(rover);
        when(roverResponsePresenter.prepareSuccessResponse(any(RoverResponseModel.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(rover.getName()).thenReturn(request.getRoverName());
        when(rover.getDirection()).thenReturn(Direction.fromValue(request.getDirection()));
        when(rover.getHorizontalPosition()).thenReturn(0);
        when(rover.getVerticalPosition()).thenReturn(0);
        when(rover.isActive()).thenReturn(true);

        when(planetGridRegisterDSGateway.findByRoverName(any())).thenReturn(planetGridResponseDS);
        when(planetGridResponseDS.getWidth()).thenReturn(5);
        when(planetGridResponseDS.getHeight()).thenReturn(5);
        when(planetGridResponseDS.getPlanetName()).thenReturn("Earth");
        when(planetGridResponseDS.getDeployedRovers()).thenReturn(Collections.emptyList());
        when(planetGridResponseDS.getObstacles()).thenReturn(Collections.emptyList());

        when(coordinateFactory.create(any(Integer.class), any(Integer.class))).thenReturn(null);
        when(planetGridFactory.create(any(), any(), any(), any(), any())).thenReturn(null);
    }
}