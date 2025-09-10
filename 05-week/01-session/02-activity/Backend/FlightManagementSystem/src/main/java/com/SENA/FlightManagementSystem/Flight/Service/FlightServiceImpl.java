package com.SENA.FlightManagementSystem.Flight.Service;

import com.SENA.FlightManagementSystem.Flight.DTO.FlightDTO;
import com.SENA.FlightManagementSystem.Flight.Entity.Flight;
import com.SENA.FlightManagementSystem.Flight.IRepository.IFlightRepository;
import com.SENA.FlightManagementSystem.Flight.IService.IFlightService;
import com.SENA.FlightManagementSystem.Flight.Utils.FlightMapper;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements IFlightService {

    private final IFlightRepository repository;
    private final FlightMapper mapper;

    public FlightServiceImpl(IFlightRepository repository, FlightMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<FlightDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public FlightDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public FlightDTO save(FlightDTO dto) {
        Flight entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
