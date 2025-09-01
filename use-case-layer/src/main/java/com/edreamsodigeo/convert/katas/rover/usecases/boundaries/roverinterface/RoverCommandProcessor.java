package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.roverinterface;

import com.edreamsodigeo.convert.katas.rover.model.request.MoveRoverRequestModel;

import java.util.List;
import java.util.regex.Pattern;

public interface RoverCommandProcessor {

    List<RoverInterfaceRequest> generateRoverInterfaceRequests(String planetName, String roverName, String command);

    boolean isCommandValid(String command);

    Pattern getPattern();

}
