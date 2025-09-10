package com.SENA.FlightManagementSystem.AircraftManagement.Utils;

import org.springframework.stereotype.Component;

import com.SENA.FlightManagementSystem.AircraftManagement.DTO.AircraftDTO;
import com.SENA.FlightManagementSystem.AircraftManagement.Entity.Arcraft;

@Component
public abstract class AircraftMapper {
    // Métodos y atributos comunes para utilidades

    public AircraftDTO toDTO(Arcraft entity) {
        AircraftDTO dto = new AircraftDTO();
        dto.setId(entity.getId());
        dto.setManufacturer(entity.getManufacturer());
        dto.setRegistrationCode(entity.getRegistrationCode());
        dto.setHoursInUse(entity.getHoursInUse());
        if (entity.getAircraftType() != null) {
            dto.setAircraftTypeId(entity.getAircraftType().getId());
            dto.setAircraftTypeName(entity.getAircraftType().getName());
        }
        return dto;
    }

    public Arcraft toEntity(AircraftDTO dto) {
        Arcraft entity = new Arcraft();
        entity.setId(dto.getId());
        entity.setManufacturer(dto.getManufacturer());
        entity.setRegistrationCode(dto.getRegistrationCode());
        entity.setHoursInUse(dto.getHoursInUse());
        // AircraftType debe cargarse desde el service
        return entity;
    }
}
