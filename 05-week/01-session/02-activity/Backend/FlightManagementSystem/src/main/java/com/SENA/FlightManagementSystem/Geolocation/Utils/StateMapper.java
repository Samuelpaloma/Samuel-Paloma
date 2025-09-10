package com.SENA.FlightManagementSystem.Geolocation.Utils;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.StateDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.State;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {

    public StateDTO toDTO(State entity) {
        StateDTO dto = new StateDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        if (entity.getCountry() != null) {
            dto.setCountryId(entity.getCountry().getId());
            dto.setCountryName(entity.getCountry().getName());
        }
        return dto;
    }

    public State toEntity(StateDTO dto) {
        State entity = new State();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        // Country relation should be loaded in the service before saving
        return entity;
    }
}
