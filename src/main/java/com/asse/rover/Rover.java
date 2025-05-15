package com.asse.rover;

import com.asse.plateau.Plateau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Rover {

    String id;
    @EqualsAndHashCode.Exclude
    Plateau plateau;
    @EqualsAndHashCode.Exclude
    int x;
    @EqualsAndHashCode.Exclude
    int y;
    @EqualsAndHashCode.Exclude
    Facing facing;

    private Rover() {
    }

    public String getPosition() {
        return x + ":" + y + ":" + facing;
    }

    public String advance() throws ObstacleException {
        int landingX = x;
        int landingY = y;
        switch (facing) {
        case N:
            landingY = (y + plateau.getHeight() + 1) % plateau.getHeight();
            break;
        case E:
            landingX = (x + plateau.getWidth() + 1) % plateau.getWidth();
            break;
        case S:
            landingY = (y + plateau.getHeight() - 1) % plateau.getHeight();
            break;
        case W:
            landingX = (x + plateau.getWidth() - 1) % plateau.getWidth();
            break;
        default:
            break;
        }
        if (plateau.isObstacle(landingX, landingY)) {
            throw new ObstacleException(this.x, this.y, this.facing);
        }
        x = landingX;
        y = landingY;
        return this.getPosition();
    }
    public void rotateLeft() {
        this.facing = facing.turnLeft();
    }

    public void rotateRight() {
        this.facing = facing.turnRight();
    }

}
