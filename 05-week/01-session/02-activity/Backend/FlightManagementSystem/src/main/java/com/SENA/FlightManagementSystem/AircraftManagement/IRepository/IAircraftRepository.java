package com.SENA.FlightManagementSystem.AircraftManagement.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SENA.FlightManagementSystem.AircraftManagement.Entity.Arcraft;

public interface IAircraftRepository extends JpaRepository<Arcraft, String> {
    // Métodos comunes para repositorios
}
