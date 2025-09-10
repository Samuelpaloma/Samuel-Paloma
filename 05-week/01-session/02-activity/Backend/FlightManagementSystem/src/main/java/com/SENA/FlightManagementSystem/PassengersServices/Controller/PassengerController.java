package com.SENA.FlightManagementSystem.PassengersServices.Controller;

import com.SENA.FlightManagementSystem.PassengersServices.Entity.Passenger;
import com.SENA.FlightManagementSystem.PassengersServices.IService.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private IPassengerService passengerService;

    // 1. Registrar pasajero
    @PostMapping("/register")
    public Passenger registerPassenger(@RequestBody Passenger passenger) {
        return passengerService.registerPassenger(passenger);
    }

    // 2. Acumular puntos de viajero frecuente
    @PutMapping("/{id}/points")
    public Passenger addPoints(@PathVariable Long id, @RequestParam int points) {
        return passengerService.addFrequentFlyerPoints(id, points);
    }

    // 3. Obtener todos los pasajeros
    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // 4. Obtener pasajero por ID
    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }
}