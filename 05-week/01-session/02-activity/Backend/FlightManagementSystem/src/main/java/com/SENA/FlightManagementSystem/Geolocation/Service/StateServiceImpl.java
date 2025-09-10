package com.SENA.FlightManagementSystem.Geolocation.Service;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.StateDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.State;
import com.SENA.FlightManagementSystem.Geolocation.IRepository.IStateRepository;
import com.SENA.FlightManagementSystem.Geolocation.IService.IStateService;
import com.SENA.FlightManagementSystem.Geolocation.Utils.StateMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements IStateService {

    private final IStateRepository repository;
    private final StateMapper mapper;

    public StateServiceImpl(IStateRepository repository, StateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<StateDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public StateDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public StateDTO save(StateDTO dto) {
        State entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
