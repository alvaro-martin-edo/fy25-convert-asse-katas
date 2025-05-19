package com.kata.rover.model.command;

import com.kata.rover.model.Direction;
import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MoveTest {

    @Test(dataProvider = "provideMoveScenarios")
    public void shouldMoveCorrectlyBasedOnDirection(Position initialPosition, Position expectedPosition) {
        Plateau plateau = new Plateau(5, 5);
        Move move = new Move();

        Position result = move.execute(initialPosition, plateau);

        assertEquals(result, expectedPosition);
    }

    @DataProvider(name = "provideMoveScenarios")
    public Object[][] provideMoveScenarios() {
        return new Object[][]{
            {new Position(2, 2, Direction.NORTH), new Position(2, 3, Direction.NORTH)},
            {new Position(2, 2, Direction.SOUTH), new Position(2, 1, Direction.SOUTH)},
            {new Position(2, 2, Direction.EAST), new Position(3, 2, Direction.EAST)},
            {new Position(2, 2, Direction.WEST), new Position(1, 2, Direction.WEST)},
            {new Position(4, 4, Direction.NORTH), new Position(4, 0, Direction.NORTH)},
            {new Position(0, 0, Direction.SOUTH), new Position(0, 4, Direction.SOUTH)},
            {new Position(4, 4, Direction.EAST), new Position(0, 4, Direction.EAST)},
            {new Position(0, 0, Direction.WEST), new Position(4, 0, Direction.WEST)},
            {new Position(8, 11, Direction.NORTH), new Position(3, 2, Direction.NORTH)}, // Wrap around
            {new Position(-1, -3, Direction.SOUTH), new Position(4, 1, Direction.SOUTH)}, // Wrap around
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenDirectionIsInvalid() {
        Plateau plateau = new Plateau(5, 5);
        Position invalidPosition = new Position(2, 2, null); // Invalid direction
        Move move = new Move();

        move.execute(invalidPosition, plateau);
    }

}
