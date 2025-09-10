package com.SENA.FlightManagementSystem.Geolocation.Utils;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.ContinentDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.Continent;
import org.springframework.stereotype.Component;

@Component
public class ContinentMapper {

    public ContinentDTO toDTO(Continent entity) {
        ContinentDTO dto = new ContinentDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Continent toEntity(ContinentDTO dto) {
        Continent entity = new Continent();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
