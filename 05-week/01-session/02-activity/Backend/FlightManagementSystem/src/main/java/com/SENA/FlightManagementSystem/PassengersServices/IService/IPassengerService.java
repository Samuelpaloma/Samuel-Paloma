package com.SENA.FlightManagementSystem.PassengersServices.IService;

import com.SENA.FlightManagementSystem.PassengersServices.Entity.Passenger;
import java.util.List;

public interface IPassengerService {
    Passenger registerPassenger(Passenger passenger);
    Passenger addFrequentFlyerPoints(Long id, int points);
    List<Passenger> getAllPassengers();
    Passenger getPassengerById(Long id);
}