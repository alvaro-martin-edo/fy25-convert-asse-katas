package com.kata.rover.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class PlateauTest {
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;

    @Test
    public void shouldCreatePlateauWithValidDimensionsWhenDimensionsArePositive() {
        Plateau plateau = new Plateau(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        assertEquals(plateau.resolvePosition(new Position(2, 2, Direction.NORTH)).getX(), 2);
        assertEquals(plateau.resolvePosition(new Position(2, 2, Direction.NORTH)).getY(), 2);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenWidthIsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> new Plateau(-1, 5));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenHeightIsNonPositive() {
        new Plateau(5, -1);
    }

    @Test(dataProvider = "providePositions")
    public void shouldWrapPositionWhenPositionIsOutOfBounds(Position initialPosition, Position expectedPosition) {
        Plateau plateau = new Plateau(5, 5);
        Position result = plateau.resolvePosition(initialPosition);

        assertEquals(result, expectedPosition); // Wrapped X-coordinate
    }

    @DataProvider(name = "providePositions")
    public Object[][] providePositions() {
        return new Object[][]{
            {new Position(0, 0, Direction.NORTH), new Position(0, 0, Direction.NORTH)},
            {new Position(5, 5, Direction.EAST), new Position(0, 0, Direction.EAST)},
            {new Position(3, 4, Direction.NORTH), new Position(3, 4, Direction.NORTH)},
            {new Position(3, 8, Direction.EAST), new Position(3, 3, Direction.EAST)},
            {new Position(-1, -3, Direction.SOUTH), new Position(4, 2, Direction.SOUTH)},
            {new Position(10, 11, Direction.WEST), new Position(0, 1, Direction.WEST)},
            {new Position(10, 3, Direction.WEST), new Position(0, 3, Direction.WEST)},
            {new Position(-1, 14, Direction.NORTH), new Position(4, 4, Direction.NORTH)},
            {new Position(14, -1, Direction.NORTH), new Position(4, 4, Direction.NORTH)},
        };
    }

}
