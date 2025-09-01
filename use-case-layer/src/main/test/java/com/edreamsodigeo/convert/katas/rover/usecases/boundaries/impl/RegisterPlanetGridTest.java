package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.impl;

import com.edreamsodigeo.convert.katas.rover.entities.PlanetGrid;
import com.edreamsodigeo.convert.katas.rover.entities.factory.PlanetGridFactory;
import com.edreamsodigeo.convert.katas.rover.model.request.RegisterPlanetGridRequestModel;
import com.edreamsodigeo.convert.katas.rover.model.response.PlanetGridResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.PlanetGridResponsePresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RegisterPlanetGridTest {

    @Mock private PlanetGridRegisterDSGateway planetGridRegisterDSGateway;
    @Mock private PlanetGridFactory planetGridFactory;
    @Mock private PlanetGridResponsePresenter planetGridResponsePresenter;
    @Mock private PlanetGrid planetGrid;

    private RegisterPlanetGrid registerPlanetGrid;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registerPlanetGrid = new RegisterPlanetGrid(
                planetGridRegisterDSGateway,
                planetGridFactory,
                planetGridResponsePresenter
        );
    }

    @Test
    public void givenAPlanetGridRegisterRequestWhenRegisteringThenPlanetGridIsRegisteredSuccessfully() {
        RegisterPlanetGridRequestModel request = new RegisterPlanetGridRequestModel("Earth", 5, 7);

        when(planetGridRegisterDSGateway.existsByName("Earth")).thenReturn(false);
        when(planetGridFactory.create(5, 7, "Earth")).thenReturn(planetGrid);
        when(planetGrid.getName()).thenReturn("Earth");
        when(planetGrid.getWidth()).thenReturn(5);
        when(planetGrid.getHeight()).thenReturn(7);
        when(planetGridResponsePresenter.prepareSuccessResponse(any(PlanetGridResponseModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PlanetGridResponseModel response = registerPlanetGrid.register(request);

        assertNotNull(response);
        assertEquals(response.getPlanetName(), "Earth");
        assertEquals(response.getSize(), "5x7");
        verify(planetGridRegisterDSGateway).save(any(PlanetGridRequestDS.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenAnExistingPlanetGridRegisterRequestWhenRegisteringThenExceptionIsThrown() {
        RegisterPlanetGridRequestModel request = new RegisterPlanetGridRequestModel("Mars", 3, 3);
        when(planetGridRegisterDSGateway.existsByName("Mars")).thenReturn(true);

        registerPlanetGrid.register(request);
    }
}