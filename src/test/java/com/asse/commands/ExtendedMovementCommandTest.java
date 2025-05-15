package com.asse.commands;

import com.asse.rover.Facing;
import com.asse.rover.Rover;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.asse.rover.RoverMother.createRoverWithPosition;
import static org.testng.Assert.assertEquals;

public class ExtendedMovementCommandTest {
    @Test(dataProvider = "extendedMovementCommandDataProvider")
    public void testExtendedMovementCommand(Rover rover, String command, String expectedPosition) {
        ExtendedMovementCommand movementCommand = new ExtendedMovementCommand(rover, command);
        assertEquals(expectedPosition, movementCommand.execute());
    }

    @DataProvider(name = "extendedMovementCommandDataProvider")
    public Object[][] movementCommandDataProvider() {
        return new Object[][]{
                {createRoverWithPosition(0, 0, Facing.N), "M", "0:1:N"},
                {createRoverWithPosition(0, 0, Facing.N), "2M", "0:2:N"},
                {createRoverWithPosition(0, 0, Facing.N), "2M2M", "0:4:N"},
                {createRoverWithPosition(0, 0, Facing.N), "2M2M2M", "0:6:N"},
                {createRoverWithPosition(0, 0, Facing.N), "2M2L2M", "0:0:S"}
        };
    }
}
