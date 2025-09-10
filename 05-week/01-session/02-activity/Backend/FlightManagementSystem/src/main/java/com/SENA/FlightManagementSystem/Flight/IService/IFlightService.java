package com.SENA.FlightManagementSystem.Flight.IService;

import com.SENA.FlightManagementSystem.Flight.DTO.FlightDTO;
import java.util.List;

public interface IFlightService {
    List<FlightDTO> findAll();
    FlightDTO findById(String id);
    FlightDTO save(FlightDTO dto);
    void delete(String id);
}
