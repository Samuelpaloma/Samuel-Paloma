package com.SENA.FlightManagementSystem.Geolocation.Controller;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.CountryDTO;
import com.SENA.FlightManagementSystem.Geolocation.IService.ICountryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geolocation/country")
public class CountryController {

    private final ICountryService service;

    public CountryController(ICountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CountryDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CountryDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public CountryDTO create(@RequestBody CountryDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public CountryDTO update(@PathVariable String id, @RequestBody CountryDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
