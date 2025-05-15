package com.asse.plateau;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PlateauReaderTest {
    @Test
    public void testReadPlateaus() {
        PlateauReader plateauReader = new PlateauReader();
        List<Plateau> plateaus = plateauReader.readPlateaus();

        assertNotNull(plateaus);
        assertFalse(plateaus.isEmpty());

        for (Plateau plateau : plateaus) {
            assertNotNull(plateau.getName());
            assertNotNull(plateau.getWidth());
            assertNotNull(plateau.getHeight());
            assertNotNull(plateau.getObstacles());
            assertTrue(plateau.getObstacles().isEmpty());
            assertNotNull(plateau.getRovers());
        }
    }
}
