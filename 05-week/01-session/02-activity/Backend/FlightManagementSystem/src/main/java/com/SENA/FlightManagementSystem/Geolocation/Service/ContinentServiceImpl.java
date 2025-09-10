package com.SENA.FlightManagementSystem.Geolocation.Service;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.ContinentDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.Continent;
import com.SENA.FlightManagementSystem.Geolocation.IRepository.IContinentRepository;
import com.SENA.FlightManagementSystem.Geolocation.IService.IContinentService;
import com.SENA.FlightManagementSystem.Geolocation.Utils.ContinentMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContinentServiceImpl implements IContinentService {

    private final IContinentRepository repository;
    private final ContinentMapper mapper;

    public ContinentServiceImpl(IContinentRepository repository, ContinentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ContinentDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ContinentDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public ContinentDTO save(ContinentDTO dto) {
        Continent entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
