package com.SENA.FlightManagementSystem.Flight.IRepository;

import com.SENA.FlightManagementSystem.Flight.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, String> {
}
