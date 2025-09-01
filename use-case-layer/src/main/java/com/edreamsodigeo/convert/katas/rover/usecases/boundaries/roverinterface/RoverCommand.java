package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface;

public enum RoverCommand {
    MOVE("move"),
    TURN_LEFT("turn_left"),
    TURN_RIGHT("turn_right"),
    STOP("stop");

    private final String command;

    RoverCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static RoverCommand fromString(String command) {
        for (RoverCommand roverCommand : RoverCommand.values()) {
            if (roverCommand.command.equalsIgnoreCase(command)) {
                return roverCommand;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
