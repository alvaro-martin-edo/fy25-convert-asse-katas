package com.kata.rover.model;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    private final Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        if (Objects.isNull(direction)) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;
        return x == position.x && y == position.y && direction == position.direction;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + Objects.hashCode(direction);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
            "x=" + x +
            ", y=" + y +
            ", direction=" + direction +
            '}';
    }
}
