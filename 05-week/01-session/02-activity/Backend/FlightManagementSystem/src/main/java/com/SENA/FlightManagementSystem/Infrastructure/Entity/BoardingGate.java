package com.SENA.FlightManagementSystem.Infrastructure.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

@Entity
@Table(name = "boarding_gate", schema = "infrastructure")
@Schema(name = "boarding_gate", description = "Entidad que representa una puerta de embarque")
public class BoardingGate extends ABaseEntity {

    @Schema(description = "Terminal a la que pertenece la puerta de embarque")
    @ManyToOne
    @JoinColumn(name = "terminal_id", nullable = false)
    private Terminal terminal;

    // Constructors
    public BoardingGate() {}

    public BoardingGate(String code, String name, Terminal terminal) {
        this.setCode(code);
        this.setName(name);
        this.terminal = terminal;
    }

    // Getters and Setters
    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    // Helper method
    public String getFullIdentification() {
        StringBuilder identification = new StringBuilder();
        if (getCode() != null && !getCode().trim().isEmpty()) {
            identification.append(getCode());
        }
        if (terminal != null && terminal.getName() != null) {
            if (identification.length() > 0) {
                identification.append(" - ");
            }
            identification.append(terminal.getName());
        }
        if (terminal != null && terminal.getAirport() != null && terminal.getAirport().getName() != null) {
            if (identification.length() > 0) {
                identification.append(" (");
            }
            identification.append(terminal.getAirport().getName()).append(")");
        }
        return identification.toString();
    }
}