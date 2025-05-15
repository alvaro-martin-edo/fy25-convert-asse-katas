package com.asse.universe;

import com.asse.plateau.Plateau;
import com.asse.plateau.PlateauReader;
import com.asse.rover.Facing;
import com.asse.rover.Rover;
import com.asse.rover.RoverDTO;
import com.asse.rover.RoverReader;

import java.util.List;
import java.util.stream.Collectors;

public class UniverseInit {

    public static Universe bigBang() {
        // Initialize the universe with plateaus and rovers
        List<Plateau> plateaus = new PlateauReader().readPlateaus();
        List<RoverDTO> roverDTOS = new RoverReader().readRovers();

        // Convert RoverDTOs to Rovers
        List<Rover> rovers = roverDTOS.stream()
                .map(roverDTO -> {
                    Plateau plateau = plateaus.stream()
                            .filter(p -> p.getName().equalsIgnoreCase(roverDTO.getPlateau()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Plateau not found: " + roverDTO.getPlateau()));
                    Rover rover = new Rover(roverDTO.getId(), plateau, roverDTO.getX(), roverDTO.getY(), Facing.fromString(roverDTO.getFacing()));
                    plateau.registerRover(rover);
                    return rover;
                })
                .collect(Collectors.toList());

        return new Universe(plateaus, rovers);
    }
}
