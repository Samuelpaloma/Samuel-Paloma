package com.SENA.FlightManagementSystem.Flight.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight", schema = "flight_operations")
@Schema(description = "Entidad que representa los vuelos")
public class Flight extends ABaseFlightEntity {

    @Column(name = "flight_date", nullable = false)
    private LocalDate flightDate;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "flight_type_id", nullable = true, length = 36)
    private String flightTypeId;

    @Column(name = "origin_boarding_gate_id", nullable = true, length = 36)
    private String originBoardingGateId;

    @Column(name = "destination_boarding_gate_id", nullable = true, length = 36)
    private String destinationBoardingGateId;

    @Column(name = "aircraft_id", nullable = true, length = 36)
    private String aircraftId;

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

    public String getOriginBoardingGateId() {
        return originBoardingGateId;
    }

    public void setOriginBoardingGateId(String originBoardingGateId) {
        this.originBoardingGateId = originBoardingGateId;
    }

    public String getDestinationBoardingGateId() {
        return destinationBoardingGateId;
    }

    public void setDestinationBoardingGateId(String destinationBoardingGateId) {
        this.destinationBoardingGateId = destinationBoardingGateId;
    }

    public String getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(String aircraftId) {
        this.aircraftId = aircraftId;
    }
}
