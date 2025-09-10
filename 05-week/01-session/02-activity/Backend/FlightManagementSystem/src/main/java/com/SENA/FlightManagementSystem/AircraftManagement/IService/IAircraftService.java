package com.SENA.FlightManagementSystem.AircraftManagement.IService;

import java.util.List;

import com.SENA.FlightManagementSystem.AircraftManagement.DTO.AircraftDTO;

public interface IAircraftService {
    // Métodos comunes para servicios
    List<AircraftDTO> findAll();
    AircraftDTO findById(String id);
    AircraftDTO save(AircraftDTO dto);
    void delete(String id);
}
