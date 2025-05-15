package com.asse.plateau;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PlateauReader {


    public List<Plateau> readPlateaus() {
        Gson gson = new Gson();
        List<Plateau> plateaus;
        try {
            FileReader fileReader = new FileReader(new File("src/main/resources/plateaus.json"));
            plateaus = List.of(gson.fromJson(fileReader, Plateau[].class));
            plateaus.stream().forEach(plateau -> {
                System.out.println("Plateau: " + plateau.getName());
                System.out.println("Width: " + plateau.getWidth());
                System.out.println("Height: " + plateau.getHeight());
                plateau.getObstacles().stream()
                        .forEach(obstacle -> System.out.println("Obstacle: " + obstacle.getX() + ":" + obstacle.getY()));
                plateau.setRovers(new ArrayList<>());
                System.out.println("..........................");
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return plateaus;
    }
}
