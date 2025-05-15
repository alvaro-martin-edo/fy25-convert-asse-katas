package com.asse.universe;

import com.asse.plateau.Plateau;
import com.asse.rover.Rover;
import lombok.Data;

import java.util.List;

@Data
public class Universe {
    private final List<Plateau> plateaus;
    private final List<Rover> rovers;

    public Universe(List<Plateau> plateaus, List<Rover> rovers) {
        this.plateaus = plateaus;
        this.rovers = rovers;
    }

    public Plateau getPlateau(String name) {
        return plateaus.stream()
                .filter(plateau -> plateau.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Plateau not found"));
    }
}
