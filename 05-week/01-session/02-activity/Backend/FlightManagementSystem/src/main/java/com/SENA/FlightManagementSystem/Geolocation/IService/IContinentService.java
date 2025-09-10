package com.SENA.FlightManagementSystem.Geolocation.IService;


import java.util.List;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.ContinentDTO;

public interface IContinentService {
    List<ContinentDTO> findAll();
    ContinentDTO findById(String id);
    ContinentDTO save(ContinentDTO dto);
    void delete(String id);
}
