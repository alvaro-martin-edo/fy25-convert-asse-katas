package com.asse.rover;

import com.asse.plateau.Plateau;
import com.asse.plateau.PlateauMother;

public class RoverMother {

    public static Rover createDefaultRover() {
        return new Rover("1", PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
    }

    public static Rover createDefaultRover(String id) {
        return new Rover(id, PlateauMother.createDefaultPlateau(), 0, 0, Facing.N);
    }

    public static Rover createRoverWithPosition(int x, int y, Facing facing) {
        return new Rover("1", PlateauMother.createDefaultPlateau(), x, y, facing);
    }

    public static Rover createRoverWithPositionAndPlateauWithObstacle(int x, int y, Facing facing, int obstacleX, int obstacleY) {
        Plateau plateauWithObstacle = PlateauMother.createPlateauWithObstacle(obstacleX, obstacleY);
        return new Rover("1", plateauWithObstacle, x, y, facing);
    }
}
