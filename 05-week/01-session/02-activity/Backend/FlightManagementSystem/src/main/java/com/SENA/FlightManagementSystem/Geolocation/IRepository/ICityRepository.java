package com.SENA.FlightManagementSystem.Geolocation.IRepository;

import com.SENA.FlightManagementSystem.Geolocation.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, String> {
}
