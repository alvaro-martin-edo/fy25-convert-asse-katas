package com.asse;

import com.asse.commands.Command;
import com.asse.commands.ExtendedMovementCommand;
import com.asse.plateau.Plateau;
import com.asse.rover.Rover;
import com.asse.universe.Universe;
import com.asse.universe.UniverseInit;

import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        Universe universe = UniverseInit.bigBang();

        universe.getPlateaus().stream()
                .forEach(plateau -> {
                    System.out.println("Plateau: " + plateau.getName());
                    plateau.toMap();
                });

        while (true) {
            System.out.println("Select plateau to operate:");
            universe.getPlateaus().stream()
                    .forEach(plateau -> System.out.println("- " + plateau.getName()));
            String plateauName = scanner.next();
            if (plateauName.isEmpty()) {
                break;
            }
            Plateau plateau = universe.getPlateau(plateauName);
            while (true) {
                System.out.println("Select rover:");
                plateau.getRovers().stream()
                        .forEach(rover -> System.out.println("- " + rover.getId()));
                String roverId = scanner.next();
                if (roverId.isEmpty()) {
                    break;
                }
                    Rover rover;
                try {
                    rover = plateau.getRover(roverId);
                }catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
                while (true) {
                    System.out.println("Enter command");
                    String command = scanner.next();
                    if (command.isEmpty()) {
                        break;
                    }
                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }
                    if (command.equalsIgnoreCase("list")) {
                        plateau.getRovers().stream()
                                .forEach(r -> System.out.println(r.getId()));
                        continue;
                    }
                    if (command.equalsIgnoreCase("map")) {
                        plateau.toMap();
                        continue;
                    }
                    try {
                        Command movementCommand = new ExtendedMovementCommand(rover, command);
                        String result = movementCommand.execute();
                        System.out.println("Result: " + result);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }
    }
}
