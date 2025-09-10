package com.SENA.FlightManagementSystem.Geolocation.Service;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CountryDTO;
import com.SENA.FlightManagementSystem.Geolocation.Entity.Country;
import com.SENA.FlightManagementSystem.Geolocation.IRepository.ICountryRepository;
import com.SENA.FlightManagementSystem.Geolocation.IService.ICountryService;
import com.SENA.FlightManagementSystem.Geolocation.Utils.CountryMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository repository;
    private final CountryMapper mapper;

    public CountryServiceImpl(ICountryRepository repository, CountryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CountryDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CountryDTO findById(String id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public CountryDTO save(CountryDTO dto) {
        Country entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
