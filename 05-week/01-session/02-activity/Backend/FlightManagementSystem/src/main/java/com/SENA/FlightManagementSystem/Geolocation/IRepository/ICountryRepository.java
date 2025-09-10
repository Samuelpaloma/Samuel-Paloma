package com.SENA.FlightManagementSystem.Geolocation.IRepository;

import com.SENA.FlightManagementSystem.Geolocation.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, String> {
}
