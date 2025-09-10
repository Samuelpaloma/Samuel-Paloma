package com.SENA.FlightManagementSystem.Geolocation.IService;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.StateDTO;
import java.util.List;

public interface IStateService {
    List<StateDTO> findAll();
    StateDTO findById(String id);
    StateDTO save(StateDTO dto);
    void delete(String id);
}
