package com.SENA.FlightManagementSystem.PassengersServices.Service;

import com.SENA.FlightManagementSystem.PassengersServices.Entity.Passenger;
import com.SENA.FlightManagementSystem.PassengersServices.IRepository.IPassengerRepository;
import com.SENA.FlightManagementSystem.PassengersServices.IService.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService implements IPassengerService {

    @Autowired
    private IPassengerRepository passengerRepository;

    @Override
    public Passenger registerPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger addFrequentFlyerPoints(Long id, int points) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger != null) {
            passenger.setFrequentFlyerPoints(passenger.getFrequentFlyerPoints() + points);
            return passengerRepository.save(passenger);
        }
        return null;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }
}