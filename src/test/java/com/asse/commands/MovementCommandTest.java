package com.asse.commands;

import com.asse.plateau.PlateauMother;
import com.asse.rover.Facing;
import com.asse.rover.Rover;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.asse.rover.RoverMother.createRoverWithPosition;
import static com.asse.rover.RoverMother.createRoverWithPositionAndPlateauWithObstacle;
import static org.testng.Assert.assertEquals;

public class MovementCommandTest {
    @Test(dataProvider = "movementCommandDataProvider")
    public void testMovementCommand(Rover rover, String command, String expectedPosition) {
        MovementCommand movementCommand = new MovementCommand(rover, command);
        assertEquals(expectedPosition, movementCommand.execute());
    }

    @DataProvider(name = "movementCommandDataProvider")
    public Object[][] movementCommandDataProvider() {
        return new Object[][]{
                {createRoverWithPosition(0, 0, Facing.N), "M", "0:1:N"},
                {createRoverWithPosition(0, 0, Facing.E), "M", "1:0:E"},
                {createRoverWithPosition(0, 0, Facing.S), "M", "0:9:S"},
                {createRoverWithPosition(0, 0, Facing.W), "M", "9:0:W"},
                {createRoverWithPosition(9, 9, Facing.N), "M", "9:0:N"},
                {createRoverWithPosition(9, 9, Facing.E), "M", "0:9:E"},
                {createRoverWithPosition(9, 9, Facing.S), "M", "9:8:S"},
                {createRoverWithPosition(9, 9, Facing.W), "M", "8:9:W"},
                {createRoverWithPosition(0, 0, Facing.N), "LM", "9:0:W"},
                {createRoverWithPosition(0, 0, Facing.N), "MMRMMLM", "2:3:N"},
                {createRoverWithPosition(0, 0, Facing.N), "MMMMMMMMMM", "0:0:N"},
                {createRoverWithPosition(0, 0, Facing.E), "MMMMMMMMMM", "0:0:E"},
                {createRoverWithPositionAndPlateauWithObstacle(0, 0, Facing.N, 0, 3), "MMMM", "O:0:2:N"},
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidCommand() {
        Rover rover = new Rover("1", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
        new MovementCommand(rover, "X");
    }
}