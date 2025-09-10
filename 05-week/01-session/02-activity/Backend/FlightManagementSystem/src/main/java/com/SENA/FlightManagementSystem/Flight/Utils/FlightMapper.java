package com.SENA.FlightManagementSystem.Flight.Utils;

import com.SENA.FlightManagementSystem.Flight.DTO.FlightDTO;
import com.SENA.FlightManagementSystem.Flight.Entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {
    public FlightDTO toDTO(Flight entity) {
        FlightDTO dto = new FlightDTO();
        dto.setId(entity.getId());
        dto.setFlightDate(entity.getFlightDate());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setArrivalTime(entity.getArrivalTime());
        return dto;
    }

    public Flight toEntity(FlightDTO dto) {
        Flight entity = new Flight();
        entity.setId(dto.getId());
        entity.setFlightDate(dto.getFlightDate());
        entity.setDepartureTime(dto.getDepartureTime());
        entity.setArrivalTime(dto.getArrivalTime());
        return entity;
    }
}
