package com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl;

import java.time.LocalDateTime;

public class ObstacleDataMapper {

    private final String type;
    private final int horizontalPosition;
    private final int verticalPosition;
    private LocalDateTime createdAt;

    public ObstacleDataMapper(String type, int horizontalPosition, int verticalPosition) {
        this.type = type;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public String getType() {
        return type;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }
}
