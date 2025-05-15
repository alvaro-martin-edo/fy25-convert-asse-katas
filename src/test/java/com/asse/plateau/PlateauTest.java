package com.asse.plateau;

import com.asse.rover.Rover;
import org.testng.annotations.Test;

import static com.asse.plateau.PlateauMother.*;
import static com.asse.rover.RoverMother.createDefaultRover;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PlateauTest {
    @Test
    public void testPlateauCreation() {
        Plateau plateau = createDefaultPlateau();
        assertEquals(plateau.getWidth(), 10);
        assertEquals(plateau.getHeight(), 10);
    }

    @Test
    public void testAddObstacle() {
        Plateau plateau = createDefaultPlateau();
        plateau.registerObstacle(5, 5);
        assertTrue(plateau.isObstacle(5, 5));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongObstacle() {
        Plateau plateau = createDefaultPlateau();
        plateau.registerObstacle(50, 50);
    }

    @Test
    public void testAddRover() {
        Plateau plateau = createDefaultPlateau();
        Rover rover = createDefaultRover();
        plateau.registerRover(rover);
        plateau.registerRover(createDefaultRover("2"));
        assertTrue(plateau.getRovers().contains(rover));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddSameRoverTwice() {
        Plateau plateau = createDefaultPlateau();
        Rover rover = createDefaultRover();
        plateau.registerRover(rover);
        plateau.registerRover(rover);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddRoverWithObstacle() {
        Plateau plateau = createDefaultPlateau();
        Rover rover = createDefaultRover();
        plateau.registerObstacle(0, 0);
        plateau.registerRover(rover);
    }
}