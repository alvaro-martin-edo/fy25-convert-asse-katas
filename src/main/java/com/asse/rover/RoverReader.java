package com.asse.rover;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.List;

public class RoverReader {

    public List<RoverDTO> readRovers() {
        Gson gson = new Gson();
        List<RoverDTO> roverDTOS;

        try {
            roverDTOS = List.of(gson.fromJson(new FileReader("src/main/resources/rovers.json"), RoverDTO[].class));
            roverDTOS.stream().forEach(rover -> {
                System.out.println("Rover: " + rover.getId());
                System.out.println("X: " + rover.getX());
                System.out.println("Y: " + rover.getY());
                System.out.println("Direction: " + rover.getFacing());
                System.out.println("Plateau: " + rover.getPlateau());
                System.out.println("..........................");
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roverDTOS;
    }

}
