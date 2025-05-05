package com.kata.rover.model;

public class Plateau {
    private final int width;
    private final int height;

    public Plateau(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    private int getWidth() {
        return width;
    }

    private int getHeight() {
        return height;
    }

    private boolean isWithinBounds(Position position) {
        return position.getX() >= 0 && position.getX() < getWidth()
            && position.getY() >= 0 && position.getY() < getHeight();
    }

    public Position resolvePosition(Position newPosition) {
        if (!isWithinBounds(newPosition)) {
            return wrapPosition(newPosition);
        }
        return newPosition;
    }

    private Position wrapPosition(Position position) {
        int newX = (position.getX() + getWidth()) % getWidth();
        int newY = (position.getY() + getHeight()) % getHeight();
        return new Position(newX, newY, position.getDirection());
    }

}
