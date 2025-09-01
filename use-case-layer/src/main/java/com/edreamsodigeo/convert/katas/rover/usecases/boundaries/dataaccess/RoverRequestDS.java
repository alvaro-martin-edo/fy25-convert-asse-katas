package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess;

import java.time.LocalDateTime;

public interface RoverRequestDS {
    String getName();

    String getDirection();

    int getHorizontalPosition();

    int getVerticalPosition();

    LocalDateTime getDeployedAt();

    LocalDateTime getLastCommandAt();

    boolean isActive();
}
