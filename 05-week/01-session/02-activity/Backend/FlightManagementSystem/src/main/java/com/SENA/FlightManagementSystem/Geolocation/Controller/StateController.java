package com.SENA.FlightManagementSystem.Geolocation.Controller;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.StateDTO;
import com.SENA.FlightManagementSystem.Geolocation.IService.IStateService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geolocation/state")
public class StateController {

    private final IStateService service;

    public StateController(IStateService service) {
        this.service = service;
    }

    @GetMapping
    public List<StateDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StateDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public StateDTO create(@RequestBody StateDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public StateDTO update(@PathVariable String id, @RequestBody StateDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
