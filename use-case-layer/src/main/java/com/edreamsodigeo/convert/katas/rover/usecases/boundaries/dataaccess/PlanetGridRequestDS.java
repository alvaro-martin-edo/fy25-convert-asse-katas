package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess;

import java.time.LocalDateTime;

public interface PlanetGridRequestDS {
    String getPlanetName();
    int getWidth();
    int getHeight();
    LocalDateTime createdAt();
    LocalDateTime lastUpdatedAt();
}
