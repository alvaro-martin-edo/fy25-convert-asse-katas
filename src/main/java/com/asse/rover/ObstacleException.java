package com.asse.rover;

public class ObstacleException extends Exception {
    int x;
    int y;
    Facing facing;

    public ObstacleException(int x, int y, Facing facing) {
        super("Obstacle detected at coordinates: " + x + ":" + y + ":" + facing);
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public String getObstacleInformation() {
        return "O:" + x + ":" + y + ":" + facing;
    }
}
