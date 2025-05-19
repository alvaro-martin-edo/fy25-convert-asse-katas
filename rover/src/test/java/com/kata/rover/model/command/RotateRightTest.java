package com.kata.rover.model.command;

import com.kata.rover.model.Direction;
import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RotateRightTest {

    @Test(dataProvider = "provideRotateRightScenarios")
    public void shouldRotateRightCorrectly(Direction initialDirection, Direction expectedDirection) {
        Plateau plateau = new Plateau(5, 5);
        Position initialPosition = new Position(2, 2, initialDirection);
        RotateRight rotateRight = new RotateRight();

        Position result = rotateRight.execute(initialPosition, plateau);

        assertEquals(result.getDirection(), expectedDirection);
    }

    @DataProvider(name = "provideRotateRightScenarios")
    public Object[][] provideRotateRightScenarios() {
        return new Object[][]{
            {Direction.NORTH, Direction.EAST},
            {Direction.EAST, Direction.SOUTH},
            {Direction.SOUTH, Direction.WEST},
            {Direction.WEST, Direction.NORTH}
        };
    }
}
