package com.edreamsodigeo.convert.katas.rover.usecases.exception;

public class PlanetGridResponseException extends RuntimeException {

    private String errorMessage;

    public PlanetGridResponseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "PlanetGridResponseException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
