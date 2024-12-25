package org.example.entity;

public class CarEntity {
    private int id;
    private int carModelId;
    private String dealershipName;
    private String state;
    private String configuration;
    private String color;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public void setDealershipName(String dealershipName) {
        this.dealershipName = dealershipName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarEntity(int id, int carModelId, String dealershipName, String state, String configuration, String color, double price) {
        this.id = id;
        this.carModelId = carModelId;
        this.dealershipName = dealershipName;
        this.state = state;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }
}
