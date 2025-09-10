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
import com.SENA.FlightManagementSystem.Infrastructure.Entity.BoardingGate;
import com.SENA.FlightManagementSystem.Infrastructure.IService.IBoardingGateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/infrastructure/boarding-gate")
public class BoardingGateController extends ABaseController<BoardingGate, IBoardingGateService> {

    public BoardingGateController(IBoardingGateService service) {
        super(service, "BoardingGate");
    }

    /**
     * Find boarding gate by name.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<Optional<BoardingGate>>> findByName(@PathVariable String name) {
        try {
            Optional<BoardingGate> boardingGate = service.findByName(name);
            return ResponseEntity.ok(new ApiResponseDto<>("Puerta de embarque encontrada", boardingGate, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find boarding gate by code.
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponseDto<Optional<BoardingGate>>> findByCode(@PathVariable String code) {
        try {
            Optional<BoardingGate> boardingGate = service.findByCode(code);
            return ResponseEntity.ok(new ApiResponseDto<>("Puerta de embarque encontrada", boardingGate, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find boarding gates by terminal.
     */
    @GetMapping("/terminal/{terminalId}")
    public ResponseEntity<ApiResponseDto<List<BoardingGate>>> findByTerminalId(@PathVariable String terminalId) {
        try {
            List<BoardingGate> boardingGates = service.findByTerminalId(terminalId);
            return ResponseEntity.ok(new ApiResponseDto<>("Puertas de embarque encontradas", boardingGates, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find boarding gates by airport.
     */
    @GetMapping("/airport/{airportId}")
    public ResponseEntity<ApiResponseDto<List<BoardingGate>>> findByAirportId(@PathVariable String airportId) {
        try {
            List<BoardingGate> boardingGates = service.findByAirportId(airportId);
            return ResponseEntity.ok(new ApiResponseDto<>("Puertas de embarque encontradas", boardingGates, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}