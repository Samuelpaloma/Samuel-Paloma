package com.SENA.FlightManagementSystem.Geolocation.Utils;


import com.SENA.FlightManagementSystem.Geolocation.Entity.Country;
import com.SENA.FlightManagementSystem.Geolocation.IDTO.CountryDTO;

import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryDTO toDTO(Country entity) {
        CountryDTO dto = new CountryDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        if (entity.getContinent() != null) {
            dto.setContinentId(entity.getContinent().getId());
            dto.setContinentName(entity.getContinent().getName());
        }
        return dto;
    }

    public Country toEntity(CountryDTO dto) {
        Country entity = new Country();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
