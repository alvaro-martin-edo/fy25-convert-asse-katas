package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommand;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessor;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BasicRoverCommandProcessor implements RoverCommandProcessor {

    private static final Pattern BASIC_COMMANDS_PATTERN = Pattern.compile("^[MRL]+$");

    @Override
    public List<RoverInterfaceRequest> generateRoverInterfaceRequests(String planetName, String roverName, String command) throws IllegalArgumentException {
        List<RoverInterfaceRequest> roverInterfaceRequests = new ArrayList<>();

        if (!isCommandValid(command)) {
            throw new IllegalArgumentException("Invalid command format: " + command);
        }

        for (char c : command.toCharArray()) {
            switch (c) {
                case 'M':
                    roverInterfaceRequests.add(new CommonRoverInterfaceRequest(
                            roverName,
                            RoverCommand.MOVE));
                    break;
                case 'L':
                    roverInterfaceRequests.add(new CommonRoverInterfaceRequest(
                            roverName,
                            RoverCommand.TURN_LEFT));
                    break;
                case 'R':
                    roverInterfaceRequests.add(new CommonRoverInterfaceRequest(
                            roverName,
                            RoverCommand.TURN_RIGHT));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command: " + c);
            }
        }
        return roverInterfaceRequests;

    }

    @Override
    public boolean isCommandValid(String command) {
        return getPattern().matcher(command).matches();
    }

    @Override
    public Pattern getPattern() {
        return BASIC_COMMANDS_PATTERN;
    }

}
