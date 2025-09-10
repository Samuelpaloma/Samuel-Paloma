package com.SENA.FlightManagementSystem.Geolocation.IRepository;

import com.SENA.FlightManagementSystem.Geolocation.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, String> {
}
