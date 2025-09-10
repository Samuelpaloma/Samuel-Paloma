package com.SENA.FlightManagementSystem.Flight.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDTO {
    private String id;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String flightTypeId;
    private String flightTypeName;
    private String originBoardingGateId;
    private String originBoardingGateName;
    private String destinationBoardingGateId;
    private String destinationBoardingGateName;
    private String aircraftId;
    private String aircraftRegistration;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getFlightTypeId() {
        return flightTypeId;
    }
    public void setFlightTypeId(String flightTypeId) {
        this.flightTypeId = flightTypeId;
    }
    public String getFlightTypeName() {
        return flightTypeName;
    }
    public void setFlightTypeName(String flightTypeName) {
        this.flightTypeName = flightTypeName;
    }
    public String getOriginBoardingGateId() {
        return originBoardingGateId;
    }
    public void setOriginBoardingGateId(String originBoardingGateId) {
        this.originBoardingGateId = originBoardingGateId;
    }
    public String getOriginBoardingGateName() {
        return originBoardingGateName;
    }
    public void setOriginBoardingGateName(String originBoardingGateName) {
        this.originBoardingGateName = originBoardingGateName;
    }
    public String getDestinationBoardingGateId() {
        return destinationBoardingGateId;
    }
    public void setDestinationBoardingGateId(String destinationBoardingGateId) {
        this.destinationBoardingGateId = destinationBoardingGateId;
    }
    public String getDestinationBoardingGateName() {
        return destinationBoardingGateName;
    }
    public void setDestinationBoardingGateName(String destinationBoardingGateName) {
        this.destinationBoardingGateName = destinationBoardingGateName;
    }
    public String getAircraftId() {
        return aircraftId;
    }
    public void setAircraftId(String aircraftId) {
        this.aircraftId = aircraftId;
    }
    public String getAircraftRegistration() {
        return aircraftRegistration;
    }
    public void setAircraftRegistration(String aircraftRegistration) {
        this.aircraftRegistration = aircraftRegistration;
    }
}
