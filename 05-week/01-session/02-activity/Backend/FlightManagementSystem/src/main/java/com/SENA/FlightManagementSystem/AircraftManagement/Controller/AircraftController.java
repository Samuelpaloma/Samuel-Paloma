package com.SENA.FlightManagementSystem.AircraftManagement.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.AircraftManagement.DTO.AircraftDTO;
import com.SENA.FlightManagementSystem.AircraftManagement.IService.IAircraftService;

@RestController
@RequestMapping("/api/aircraft-management/aircraft")

public abstract class AircraftController {
    // Métodos y atributos comunes para controladores

    private final IAircraftService service;

    public AircraftController(IAircraftService service) {
        this.service = service;
    }

    @GetMapping
    public List<AircraftDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AircraftDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public AircraftDTO create(@RequestBody AircraftDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public AircraftDTO update(@PathVariable String id, @RequestBody AircraftDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
