package com.edreamsodigeo.convert.katas.rover.usecases.exception;

public class RoverResponseException extends RuntimeException {

    private String errorMessage;

    public RoverResponseException(String errorMessage) {
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
        return "RoverResponseException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
