package com.asse.commands;

import com.asse.rover.ObstacleException;
import com.asse.rover.Rover;

import java.util.regex.Pattern;

public class ExtendedMovementCommand implements Command {
    Pattern pattern = Pattern.compile("^(\\d*[MRL])+$");

    Rover rover;
    String movementCommand;

    public ExtendedMovementCommand(Rover rover, String movementCommand) throws IllegalArgumentException {
        this.rover = rover;
        this.movementCommand = movementCommand;
        if (!pattern.matcher(movementCommand).matches()) {
            throw new IllegalArgumentException("Invalid movement command: " + movementCommand);
        }
    }

    @Override
    public String execute() {
        int accomulativeMovement = 0;
        for (char command : movementCommand.toCharArray()) {
            if (Character.isDigit(command)) {
                accomulativeMovement = Character.getNumericValue(command);
                continue;
            }
            do {
                switch (command) {
                case 'M':
                    try {
                        rover.advance();
                    } catch (ObstacleException e) {
                        return e.getObstacleInformation();
                    }
                    break;
                case 'L':
                    rover.rotateLeft();
                    break;
                case 'R':
                    rover.rotateRight();
                    break;
                default:
                    continue;
                }
            } while (--accomulativeMovement > 0);
            accomulativeMovement = 0;
        }
        return this.rover.getPosition();
    }
}
