package com.asse.plateau;

import com.asse.rover.Rover;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Plateau {
    private final String name;
    @EqualsAndHashCode.Exclude
    private final int width;
    @EqualsAndHashCode.Exclude
    private final int height;
    @EqualsAndHashCode.Exclude
    private List<Obstacle> obstacles = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    private List<Rover> rovers = new ArrayList<>();

    public Plateau(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public boolean isObstacle(int x, int y) {
        Optional<Obstacle> first = obstacles.stream()
                .filter(obstacle -> obstacle.getX() == x && obstacle.getY() == y)
                .findFirst();
        return first.isPresent();
    }

    public boolean isRover(int x, int y) {
        Optional<Rover> first = rovers.stream()
                .filter(rover -> rover.getX() == x && rover.getY() == y)
                .findFirst();
        return first.isPresent();
    }

    public Rover getRover(String id) {
        return rovers.stream()
                .filter(rover -> rover.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rover not found"));
    }

    public void registerObstacle(int x, int y) throws IllegalArgumentException{
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Obstacle coordinates out of bounds");
        }
        obstacles.add(new Obstacle(x, y));
    }

    public void registerRover(Rover rover) throws IllegalArgumentException {
        if (rovers.contains(rover)) {
            throw new IllegalArgumentException("Rover already registered");
        }
        if (isObstacle(rover.getX(), rover.getY())) {
            throw new IllegalArgumentException("Rover coordinates are on an obstacle: " + rover.getId() + " " + rover.getX() + ":" + rover.getY());
        }
        rovers.add(rover);
    }

    public void toMap() {
        System.out.println("Plateau Map:");
        for (int y = height - 1; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 0; x < width; x++) {
                if (isObstacle(x, y)) {
                    System.out.print("X ");
                } else if (isRover(x, y)) {
                    System.out.print("R ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int x = 0; x < width; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
