package com.asse.rover;

import java.util.List;

import static org.testng.Assert.*;

public class RoverReaderTest {
    @org.testng.annotations.Test
    public void testReadRovers() {
        RoverReader roverReader = new RoverReader();
        List<RoverDTO> rovers = roverReader.readRovers();

        assertNotNull(rovers);
        assertFalse(rovers.isEmpty());

        for (RoverDTO rover : rovers) {
            assertNotNull(rover.getId());
            assertNotNull(rover.getX());
            assertNotNull(rover.getY());
            assertNotNull(rover.getFacing());
            assertNotNull(rover.getPlateau());
        }
    }

}