package com.SENA.FlightManagementSystem.PassengersServices.IDTO;

public class PassengerDTO {
    private Long id;
    private String name;
    private String email;
    private String documentNumber;
    private int frequentFlyerPoints;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public int getFrequentFlyerPoints() {
        return frequentFlyerPoints;
    }
    public void setFrequentFlyerPoints(int frequentFlyerPoints) {
        this.frequentFlyerPoints = frequentFlyerPoints;
    }

}