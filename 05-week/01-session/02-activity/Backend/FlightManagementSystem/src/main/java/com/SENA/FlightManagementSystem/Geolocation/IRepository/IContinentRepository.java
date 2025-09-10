package com.SENA.FlightManagementSystem.Geolocation.IRepository;

import com.SENA.FlightManagementSystem.Geolocation.Entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContinentRepository extends JpaRepository<Continent, String> {
}
