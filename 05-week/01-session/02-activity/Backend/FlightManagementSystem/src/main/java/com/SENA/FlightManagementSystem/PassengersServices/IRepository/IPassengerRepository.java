package com.SENA.FlightManagementSystem.PassengersServices.IRepository;

import com.SENA.FlightManagementSystem.PassengersServices.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByEmail(String email);
}