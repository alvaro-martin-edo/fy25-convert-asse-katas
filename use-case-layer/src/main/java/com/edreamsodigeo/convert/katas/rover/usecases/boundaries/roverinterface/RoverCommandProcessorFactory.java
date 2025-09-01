package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface;

public interface RoverCommandProcessorFactory {
    public RoverCommandProcessor getProcessor(String command);
}
