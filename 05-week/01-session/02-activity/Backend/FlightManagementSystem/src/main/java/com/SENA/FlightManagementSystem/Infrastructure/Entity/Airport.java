
package com.SENA.FlightManagementSystem.Infrastructure.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;
import com.SENA.FlightManagementSystem.Geolocation.Entity.City;

@Entity
@Table(name = "airport", schema = "infrastructure")
@Schema(name = "airport", description = "Entidad que representa un aeropuerto")
public class Airport extends ABaseEntity {

    @Schema(description = "Dirección del aeropuerto", example = "Avenida El Dorado # 103-9")
    @Column(name = "address", nullable = true, length = 255)
    private String address;

    @Schema(description = "Ciudad donde se encuentra el aeropuerto")
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    // Constructors
    public Airport() {}

    public Airport(String code, String name, String address, City city) {
        this.setCode(code);
        this.setName(name);
        this.address = address;
        this.city = city;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // Helper method
    public String getFullLocation() {
        StringBuilder location = new StringBuilder();
        if (address != null && !address.trim().isEmpty()) {
            location.append(address);
        }
        if (city != null) {
            if (location.length() > 0) {
                location.append(", ");
            }
            location.append(city.getName());
        }
        return location.toString();
    }
}