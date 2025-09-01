package com.edreamsodigeo.convert.katas.rover.model.response;

public class PlanetGridResponseModel {

    private String planetName;
    private String size;
    private String obstacles;

    public PlanetGridResponseModel(String planetName, String size, String obstacles) {
        this.planetName = planetName;
        this.size = size;
        this.obstacles = obstacles;
    }
    public String getPlanetName() {
        return planetName;
    }
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getObstacles() {
        return obstacles;
    }
    public void setObstacles(String obstacles) {
        this.obstacles = obstacles;
    }
    @Override
    public String toString() {
        return "PlanetGridResponseModel{" +
                "planetName='" + planetName + '\'' +
                ", size='" + size + '\'' +
                ", obstacles='" + obstacles + '\'' +
                '}';
    }

}
