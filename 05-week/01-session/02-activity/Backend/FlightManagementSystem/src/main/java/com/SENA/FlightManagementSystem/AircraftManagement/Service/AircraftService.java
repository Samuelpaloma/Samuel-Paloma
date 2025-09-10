package com.SENA.FlightManagementSystem.AircraftManagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.AircraftManagement.DTO.AircraftDTO;
import com.SENA.FlightManagementSystem.AircraftManagement.Entity.Arcraft;
import com.SENA.FlightManagementSystem.AircraftManagement.IRepository.IAircraftRepository;
import com.SENA.FlightManagementSystem.AircraftManagement.IService.IAircraftService;
import com.SENA.FlightManagementSystem.AircraftManagement.Utils.AircraftMapper;

@Service

public abstract class AircraftService implements IAircraftService {
    // Métodos y atributos comunes para servicios

     private final IAircraftRepository repository;
    private final AircraftMapper mapper;

    public AircraftService(IAircraftRepository repository, AircraftMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AircraftDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AircraftDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public AircraftDTO save(AircraftDTO dto) {
        Arcraft entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
