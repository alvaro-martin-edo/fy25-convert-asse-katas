package com.asse.rover;

import lombok.Data;

@Data
public class RoverDTO {
    private String id;
    private int x;
    private int y;
    private String facing;
    private String plateau;
}
