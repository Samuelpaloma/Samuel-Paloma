package com.SENA.FlightManagementSystem.Geolocation.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "country", schema = "geolocation")
@Schema(description = "Entidad que representa los países")
public class Country extends ABaseGeolocationEntity {

    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_country_continent"))
    private Continent continent;

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
