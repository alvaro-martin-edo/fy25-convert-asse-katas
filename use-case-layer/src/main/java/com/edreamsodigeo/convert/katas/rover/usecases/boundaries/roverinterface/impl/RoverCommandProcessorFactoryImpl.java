package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessor;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface.RoverCommandProcessorFactory;

import java.util.Arrays;
import java.util.List;

public class RoverCommandProcessorFactoryImpl implements RoverCommandProcessorFactory {

    private static final List<RoverCommandProcessor> processors = Arrays.asList(
            new ExtendedRoverCommandProcessor(),
            new BasicRoverCommandProcessor()
    );

    public RoverCommandProcessor getProcessor(String command) {
        return processors.stream()
                .filter(processor -> processor.isCommandValid(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for command: " + command));
    }
}
