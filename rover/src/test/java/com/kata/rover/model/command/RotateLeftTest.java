package com.kata.rover.model.command;

import com.kata.rover.model.Direction;
import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RotateLeftTest {

    @Test(dataProvider = "provideRotateLeftScenarios")
    public void shouldRotateLeftCorrectly(Direction initialDirection, Direction expectedDirection) {
        Plateau plateau = new Plateau(5, 5);
        Position initialPosition = new Position(2, 2, initialDirection);
        RotateLeft rotateLeft = new RotateLeft();

        Position result = rotateLeft.execute(initialPosition, plateau);

        assertEquals(result.getDirection(), expectedDirection);
    }

    @DataProvider(name = "provideRotateLeftScenarios")
    public Object[][] provideRotateLeftScenarios() {
        return new Object[][]{
            {Direction.NORTH, Direction.WEST},
            {Direction.WEST, Direction.SOUTH},
            {Direction.SOUTH, Direction.EAST},
            {Direction.EAST, Direction.NORTH}
        };
    }
}
