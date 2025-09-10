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
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Terminal;
import com.SENA.FlightManagementSystem.Infrastructure.IService.ITerminalService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/infrastructure/terminal")
public class TerminalController extends ABaseController<Terminal, ITerminalService> {

    public TerminalController(ITerminalService service) {
        super(service, "Terminal");
    }

    /**
     * Find terminal by name.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<Optional<Terminal>>> findByName(@PathVariable String name) {
        try {
            Optional<Terminal> terminal = service.findByName(name);
            return ResponseEntity.ok(new ApiResponseDto<>("Terminal encontrada", terminal, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find terminal by code.
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponseDto<Optional<Terminal>>> findByCode(@PathVariable String code) {
        try {
            Optional<Terminal> terminal = service.findByCode(code);
            return ResponseEntity.ok(new ApiResponseDto<>("Terminal encontrada", terminal, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find terminals by airport.
     */
    @GetMapping("/airport/{airportId}")
    public ResponseEntity<ApiResponseDto<List<Terminal>>> findByAirportId(@PathVariable String airportId) {
        try {
            List<Terminal> terminals = service.findByAirportId(airportId);
            return ResponseEntity.ok(new ApiResponseDto<>("Terminales encontradas", terminals, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}