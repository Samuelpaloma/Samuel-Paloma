package com.SENA.FlightManagementSystem.Infrastructure.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.Parameterization.Controller.ABaseController;
import com.SENA.FlightManagementSystem.Parameterization.DTO.ApiResponseDto;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Airport;
import com.SENA.FlightManagementSystem.Infrastructure.IService.IAirportService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/infrastructure/airport")
public class AirportController extends ABaseController<Airport, IAirportService> {

    public AirportController(IAirportService service) {
        super(service, "Airport");
    }

    /**
     * Find airport by name.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<Optional<Airport>>> findByName(@PathVariable String name) {
        try {
            Optional<Airport> airport = service.findByName(name);
            return ResponseEntity.ok(new ApiResponseDto<>("Aeropuerto encontrado", airport, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find airport by code.
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponseDto<Optional<Airport>>> findByCode(@PathVariable String code) {
        try {
            Optional<Airport> airport = service.findByCode(code);
            return ResponseEntity.ok(new ApiResponseDto<>("Aeropuerto encontrado", airport, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find airports by city.
     */
    @GetMapping("/city/{cityId}")
    public ResponseEntity<ApiResponseDto<List<Airport>>> findByCityId(@PathVariable String cityId) {
        try {
            List<Airport> airports = service.findByCityId(cityId);
            return ResponseEntity.ok(new ApiResponseDto<>("Aeropuertos encontrados", airports, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}