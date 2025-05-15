package com.asse.plateau;

public class PlateauMother {
    public static Plateau createDefaultPlateau() {
        return new Plateau("Mars", 10, 10);
    }

    public static Plateau createDefaultPlateau(String name) {
        return new Plateau(name, 10, 10);
    }

    public static Plateau createPlateauWithObstacle(int obstacleX, int obstacleY) {
        Plateau plateau = createDefaultPlateau();
        plateau.registerObstacle(obstacleX, obstacleY);
        return plateau;
    }

}