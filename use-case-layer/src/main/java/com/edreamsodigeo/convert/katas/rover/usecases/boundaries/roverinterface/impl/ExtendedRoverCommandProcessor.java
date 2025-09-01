package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommand;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessor;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverInterfaceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedRoverCommandProcessor implements RoverCommandProcessor {

    private static final Pattern EXTENDED_COMMANDS_PATTERN = Pattern.compile("^(\\d+[MRL])+$");

    @Override
    public List<RoverInterfaceRequest> generateRoverInterfaceRequests(String planetName, String roverName, String command) {
        if (!isCommandValid(command)) {
            throw new IllegalArgumentException("Invalid command format: " + command);
        }

        List<RoverInterfaceRequest> requests = new ArrayList<>();
        Matcher matcher = EXTENDED_COMMANDS_PATTERN.matcher(command);

        while (matcher.find()) {
            int count = Integer.parseInt(matcher.group(1));
            char cmd = matcher.group(2).charAt(0);
            RoverCommand roverCommand = switch (cmd) {
                case 'M' -> RoverCommand.MOVE;
                case 'L' -> RoverCommand.TURN_LEFT;
                case 'R' -> RoverCommand.TURN_RIGHT;
                default -> throw new IllegalArgumentException("Invalid command: " + cmd);
            };
            for (int i = 0; i < count; i++) {
                requests.add(new CommonRoverInterfaceRequest(roverName, roverCommand));
            }
        }
        return requests;
    }

    @Override
    public boolean isCommandValid(String command) {
        return getPattern().matcher(command).matches();
    }

    @Override
    public Pattern getPattern() {
        return EXTENDED_COMMANDS_PATTERN;
    }
}
