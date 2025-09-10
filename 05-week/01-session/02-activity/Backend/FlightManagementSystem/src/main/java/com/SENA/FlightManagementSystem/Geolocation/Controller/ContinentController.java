package com.SENA.FlightManagementSystem.Geolocation.Controller;

import com.SENA.FlightManagementSystem.Geolocation.IDTO.ContinentDTO;
import com.SENA.FlightManagementSystem.Geolocation.IService.IContinentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geolocation/continent")
public class ContinentController {

    private final IContinentService service;

    public ContinentController(IContinentService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContinentDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ContinentDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public ContinentDTO create(@RequestBody ContinentDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ContinentDTO update(@PathVariable String id, @RequestBody ContinentDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
