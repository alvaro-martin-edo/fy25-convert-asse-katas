package com.edreamsodigeo.convert.katas.rover.entities.impl;

import com.edreamsodigeo.convert.katas.rover.entities.Coordinate;
import com.edreamsodigeo.convert.katas.rover.entities.Direction;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class MarsRoverTest {

    @Mock
    private Coordinate coordinate;

    @BeforeClass
    private void setup() {
        MockitoAnnotations.openMocks(this);
        when(coordinate.getHorizontalPosition()).thenReturn(0);
        when(coordinate.getVerticalPosition()).thenReturn(0);
    }

    @Test
    public void whenCreatingMarsRoverThenShouldCreateRover() {
        MarsRover marsRover = new MarsRover("Mars Rover", Direction.NORTH, coordinate, true);

        assert marsRover.getName().equals("Mars Rover");
        assert marsRover.getDirection() == Direction.NORTH;
        assert marsRover.getPosition().getHorizontalPosition() == 0;
        assert marsRover.getPosition().getVerticalPosition() == 0;
    }


}