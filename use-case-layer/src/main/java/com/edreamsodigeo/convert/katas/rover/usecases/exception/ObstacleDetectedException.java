package com.edreamsodigeo.convert.katas.rover.usecases.exception;

public class ObstacleDetectedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObstacleDetectedException(String message) {
        super(message);
    }

    public ObstacleDetectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
