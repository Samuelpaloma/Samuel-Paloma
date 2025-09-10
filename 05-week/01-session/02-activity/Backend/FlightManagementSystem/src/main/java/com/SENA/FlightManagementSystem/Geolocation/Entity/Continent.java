package com.SENA.FlightManagementSystem.Geolocation.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "continent", schema = "geolocation")
@Schema(description = "Entidad que representa los continentes")
public class Continent extends ABaseGeolocationEntity {
}
