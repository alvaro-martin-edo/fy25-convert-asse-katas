package com.edreamsodigeo.convert.katas.rover.usecases.presenter;

import com.edreamsodigeo.convert.katas.rover.model.response.RoverResponseModel;
import com.edreamsodigeo.convert.katas.rover.usecases.exception.RoverResponseException;
import com.edreamsodigeo.convert.katas.rover.usecases.presenter.impl.RoverResponseFormatter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RoverResponseModelFormatterTest {

    private RoverResponseFormatter roverResponseFormatter;

    @BeforeMethod
    public void setUp() {
        roverResponseFormatter = new RoverResponseFormatter();
    }

    @Test
    public void testPrepareSuccessResponse() {
        // Given
        RoverResponseModel response = new RoverResponseModel("Rover1", "3:0:N", "MMM", "", true);

        // When
        RoverResponseModel formattedResponse = roverResponseFormatter.prepareSuccessResponse(response);

        // Then
        assertEquals(formattedResponse.getLastCommand(), "Command: MMM");
        assertEquals(formattedResponse.getLocation(), "Location: 3:0:N");
    }

    @Test(expectedExceptions = RoverResponseException.class)
    public void testPrepareErrorResponse() {
        // Given
        String errorMessage = "Rover not found";

        // When
        roverResponseFormatter.prepareErrorResponse(errorMessage);
    }

}