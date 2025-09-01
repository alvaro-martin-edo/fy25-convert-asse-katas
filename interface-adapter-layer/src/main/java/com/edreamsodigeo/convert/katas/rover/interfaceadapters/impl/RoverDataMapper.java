package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

import java.time.LocalDateTime;

public class RoverDataMapper {
    private String name;
    private int verticalPosition;
    private int horizontalPosition;
    private String direction;
    private LocalDateTime deployedAt;
    private LocalDateTime lastCommandAt;
    private boolean active;

    public RoverDataMapper(String name, int verticalPosition, int horizontalPosition, String direction,
                           LocalDateTime deployedAt, LocalDateTime lastCommandAt, boolean active) {
        this.name = name;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.direction = direction;
        this.deployedAt = deployedAt;
        this.lastCommandAt = lastCommandAt;
        this.active = active;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getVerticalPosition() {
        return verticalPosition;
    }
    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }
    public int getHorizontalPosition() {
        return horizontalPosition;
    }
    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public LocalDateTime getDeployedAt() {
        return deployedAt;
    }
    public void setDeployedAt(LocalDateTime deployedAt) {
        this.deployedAt = deployedAt;
    }
    public LocalDateTime getLastCommandAt() {
        return lastCommandAt;
    }
    public void setLastCommandAt(LocalDateTime lastCommandAt) {
        this.lastCommandAt = lastCommandAt;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }



}
