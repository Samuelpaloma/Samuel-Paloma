package com.SENA.FlightManagementSystem.Geolocation.IService;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CityDTO;
import java.util.List;

public interface ICityService {
    List<CityDTO> findAll();
    CityDTO findById(String id);
    CityDTO save(CityDTO dto);
    void delete(String id);
}
