package com.SENA.FlightManagementSystem.Geolocation.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "city", schema = "geolocation")
@Schema(description = "Entidad que representa las ciudades")
public class City extends ABaseGeolocationEntity {

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_city_state"))
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    
}
