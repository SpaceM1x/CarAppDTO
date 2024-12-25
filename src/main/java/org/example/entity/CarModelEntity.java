package org.example.entity;

public class CarModelEntity {
    private int id;
    private String brand;
    private String model;
    private String countryOrigin;
    private String countryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CarModelEntity(int id, String brand, String model, String countryOrigin, String countryCode) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.countryOrigin = countryOrigin;
        this.countryCode = countryCode;
    }

}
