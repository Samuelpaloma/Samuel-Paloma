package com.SENA.FlightManagementSystem.Geolocation.IService;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CountryDTO;
import java.util.List;

public interface ICountryService {
    List<CountryDTO> findAll();
    CountryDTO findById(String id);
    CountryDTO save(CountryDTO dto);
    void delete(String id);
}
