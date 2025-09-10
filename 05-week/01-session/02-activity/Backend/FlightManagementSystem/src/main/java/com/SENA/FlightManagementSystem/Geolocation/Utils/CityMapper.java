package com.SENA.FlightManagementSystem.Geolocation.Utils;


import com.SENA.FlightManagementSystem.Geolocation.Entity.City;
import com.SENA.FlightManagementSystem.Geolocation.IDTO.CityDTO;

import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDTO toDTO(City entity) {
        CityDTO dto = new CityDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        if (entity.getState() != null) {
            dto.setStateId(entity.getState().getId());
            dto.setStateName(entity.getState().getName());
        }
        return dto;
    }

    public City toEntity(CityDTO dto) {
        City entity = new City();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
