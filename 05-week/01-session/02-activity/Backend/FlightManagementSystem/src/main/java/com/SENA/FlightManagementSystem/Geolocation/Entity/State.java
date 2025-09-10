package com.SENA.FlightManagementSystem.Geolocation.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "state", schema = "geolocation")
@Schema(description = "Entidad que representa los estados/departamentos")
public class State extends ABaseGeolocationEntity {

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_state_country"))
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
