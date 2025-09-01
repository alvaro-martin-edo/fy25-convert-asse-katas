package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;

import java.time.LocalDateTime;

public class RoverRequestDSImpl implements RoverRequestDS {

    private String name;
    private String direction;
    private int horizontalPosition;
    private int verticalPosition;
    private LocalDateTime deployedAt;
    private LocalDateTime lastCommandAt;
    private boolean active;

    public RoverRequestDSImpl(String name, String direction, int horizontalPosition, int verticalPosition,
                              LocalDateTime deployedAt, LocalDateTime lastCommandAt, boolean active) {
        this.name = name;
        this.direction = direction;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.deployedAt = deployedAt;
        this.lastCommandAt = lastCommandAt;
        this.active = active;
    }

    public RoverRequestDSImpl(String name, String direction, int horizontalPosition, int verticalPosition,
                              LocalDateTime lastCommandAt) {
        this.name = name;
        this.direction = direction;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.lastCommandAt = lastCommandAt;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public int getVerticalPosition() {
        return verticalPosition;
    }

    @Override
    public LocalDateTime getDeployedAt() {
        return deployedAt;
    }

    @Override
    public LocalDateTime getLastCommandAt() {
        return lastCommandAt;
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
