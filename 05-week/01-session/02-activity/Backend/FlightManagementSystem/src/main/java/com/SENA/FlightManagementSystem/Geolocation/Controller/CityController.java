package com.SENA.FlightManagementSystem.Geolocation.Controller;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CityDTO;
import com.SENA.FlightManagementSystem.Geolocation.IService.ICityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geolocation/city")
public class CityController {

    private final ICityService service;

    public CityController(ICityService service) {
        this.service = service;
    }

    @GetMapping
    public List<CityDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public CityDTO create(@RequestBody CityDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public CityDTO update(@PathVariable String id, @RequestBody CityDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
