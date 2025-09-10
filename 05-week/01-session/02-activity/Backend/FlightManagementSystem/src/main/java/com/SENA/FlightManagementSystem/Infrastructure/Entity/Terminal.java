package com.SENA.FlightManagementSystem.Infrastructure.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

@Entity
@Table(name = "terminal", schema = "infrastructure")
@Schema(name = "terminal", description = "Entidad que representa una terminal de aeropuerto")
public class Terminal extends ABaseEntity {

    @Schema(description = "Aeropuerto al que pertenece la terminal")
    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    // Constructors
    public Terminal() {}

    public Terminal(String code, String name, Airport airport) {
        this.setCode(code);
        this.setName(name);
        this.airport = airport;
    }

    // Getters and Setters
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    // Helper method
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (getName() != null && !getName().trim().isEmpty()) {
            fullName.append(getName());
        }
        if (airport != null && airport.getName() != null) {
            if (fullName.length() > 0) {
                fullName.append(" - ");
            }
            fullName.append(airport.getName());
        }
        return fullName.toString();
    }
}