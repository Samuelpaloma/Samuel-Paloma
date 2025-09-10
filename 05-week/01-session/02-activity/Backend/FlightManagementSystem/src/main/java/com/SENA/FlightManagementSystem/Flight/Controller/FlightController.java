package com.SENA.FlightManagementSystem.Flight.Controller;

import com.SENA.FlightManagementSystem.Flight.DTO.FlightDTO;
import com.SENA.FlightManagementSystem.Flight.IService.IFlightService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flight-operations/flight")
public class FlightController {

    private final IFlightService service;

    public FlightController(IFlightService service) {
        this.service = service;
    }

    @GetMapping
    public List<FlightDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public FlightDTO create(@RequestBody FlightDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public FlightDTO update(@PathVariable String id, @RequestBody FlightDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
