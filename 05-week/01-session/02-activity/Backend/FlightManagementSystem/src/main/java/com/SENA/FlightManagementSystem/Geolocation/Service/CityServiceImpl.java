package com.SENA.FlightManagementSystem.Geolocation.Service;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CityDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.City;
import com.SENA.FlightManagementSystem.Geolocation.IRepository.ICityRepository;
import com.SENA.FlightManagementSystem.Geolocation.IService.ICityService;
import com.SENA.FlightManagementSystem.Geolocation.Utils.CityMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements ICityService {

    private final ICityRepository repository;
    private final CityMapper mapper;

    public CityServiceImpl(ICityRepository repository, CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CityDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CityDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public CityDTO save(CityDTO dto) {
        City entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
