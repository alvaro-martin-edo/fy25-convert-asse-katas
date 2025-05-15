package com.asse.commands;

import com.asse.rover.ObstacleException;
import com.asse.rover.Rover;

import java.util.regex.Pattern;

public class MovementCommand implements Command {

    Pattern pattern = Pattern.compile("^[MRL]+$");
    Rover rover;
    String movementCommand;

    public MovementCommand(Rover rover, String movementCommand) throws IllegalArgumentException {
        this.rover = rover;
        this.movementCommand = movementCommand;
        if (!pattern.matcher(movementCommand).matches()) {
            throw new IllegalArgumentException("Invalid movement command: " + movementCommand);
        }
    }

    @Override
    public String execute() {
        for (char command : movementCommand.toCharArray()) {
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
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        return this.rover.getPosition();
    }
}
