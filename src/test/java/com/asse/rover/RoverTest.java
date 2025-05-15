package com.asse.rover;


import com.asse.plateau.PlateauMother;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.asse.rover.RoverMother.createRoverWithPosition;
import static com.asse.rover.RoverMother.createRoverWithPositionAndPlateauWithObstacle;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class RoverTest {


    @Test(dataProvider = "advanceDataProvider")
    public void testAdvance(Rover rover, String expectedPosition) throws ObstacleException {
        assertEquals(rover.advance(), expectedPosition);
    }

    @DataProvider(name = "advanceDataProvider")
    public static Object[][] advanceDataProvider() {
        return new Object[][]{
                {createRoverWithPosition(0, 0, Facing.N), "0:1:N"},
                {createRoverWithPosition(0, 0, Facing.E), "1:0:E"},
                {createRoverWithPosition(0, 0, Facing.S), "0:9:S"},
                {createRoverWithPosition(0, 0, Facing.W), "9:0:W"},
                {createRoverWithPosition(9, 9, Facing.N), "9:0:N"},
                {createRoverWithPosition(9, 9, Facing.E), "0:9:E"},
                {createRoverWithPosition(9, 9, Facing.S), "9:8:S"},
                {createRoverWithPosition(9, 9, Facing.W), "8:9:W"},
        };
    }

    @Test(dataProvider = "rotateLeftDataProvider")
    public void testRotateLeft(Rover rover, String expectedPosition) {
        rover.rotateLeft();
        assertEquals(rover.getPosition(), expectedPosition);
    }

    @DataProvider(name = "rotateLeftDataProvider")
    public static Object[][] rotateLeftDataProvider() {
        return new Object[][]{
                {createRoverWithPosition(0, 0, Facing.N), "0:0:W"},
                {createRoverWithPosition(0, 0, Facing.E), "0:0:N"},
                {createRoverWithPosition(0, 0, Facing.S), "0:0:E"},
                {createRoverWithPosition(0, 0, Facing.W), "0:0:S"}
        };
    }

    @Test(dataProvider = "rotateRightDataProvider")
    public void testRotateRight(Rover rover, String expectedPosition) {
        rover.rotateRight();
        assertEquals(rover.getPosition(), expectedPosition);
    }

    @DataProvider(name = "rotateRightDataProvider")
    public static Object[][] rotateRightDataProvider() {
        return new Object[][]{
                {createRoverWithPosition(0, 0, Facing.N), "0:0:E"},
                {createRoverWithPosition(0, 0, Facing.E), "0:0:S"},
                {createRoverWithPosition(0, 0, Facing.S), "0:0:W"},
                {createRoverWithPosition(0, 0, Facing.W), "0:0:N"}
        };
    }

    @Test
    public void testAdvanceWithObstacle() {
        Rover rover = createRoverWithPositionAndPlateauWithObstacle(0, 0, Facing.N, 0, 1);
        try {
            rover.advance();
        } catch (ObstacleException e) {
            assertEquals(rover.getPosition(), "0:0:N");
            assertEquals(e.getObstacleInformation(), "O:0:0:N");
        }
    }

    @Test
    public void testEqualsRover() {
        Rover rover1 = new Rover("1", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
        Rover rover2 = new Rover("1", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
        assertEquals(rover1, rover2);
        Rover rover3 = new Rover("1", PlateauMother.createDefaultPlateau(), 1, 1, Facing.E);
        assertEquals(rover1, rover3);
    }

    @Test
    public void testNotEqualsRover() {
        Rover rover1 = new Rover("1", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
        Rover rover2 = new Rover("2", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
        assertNotEquals(rover1, rover2);
    }
}
