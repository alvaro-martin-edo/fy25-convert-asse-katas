package com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS;

import java.time.LocalDateTime;

public class PlanetGridRequestDSImpl implements PlanetGridRequestDS {
    private final String planetName;
    private final int width;
    private final int height;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdatedAt;

    public PlanetGridRequestDSImpl(String planetName, int width, int height, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.planetName = planetName;
        this.width = width;
        this.height = height;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String getPlanetName() {
        return planetName;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public LocalDateTime createdAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime lastUpdatedAt() {
        return lastUpdatedAt;
    }


}
