package com.SENA.FlightManagementSystem.AircraftManagement.DTO;

public class AircraftDTO {
    private String id;
    private String manufacturer;
    private String registrationCode;
    private Integer hoursInUse;
    private String aircraftTypeId;
    private String aircraftTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Integer getHoursInUse() {
        return hoursInUse;
    }

    public void setHoursInUse(Integer hoursInUse) {
        this.hoursInUse = hoursInUse;
    }

    public String getAircraftTypeId() {
        return aircraftTypeId;
    }

    public void setAircraftTypeId(String aircraftTypeId) {
        this.aircraftTypeId = aircraftTypeId;
    }

    public String getAircraftTypeName() {
        return aircraftTypeName;
    }

    public void setAircraftTypeName(String aircraftTypeName) {
        this.aircraftTypeName = aircraftTypeName;
    }
}
