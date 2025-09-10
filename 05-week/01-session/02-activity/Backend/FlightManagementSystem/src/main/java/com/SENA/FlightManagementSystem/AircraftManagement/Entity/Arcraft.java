package com.SENA.FlightManagementSystem.AircraftManagement.Entity;

import com.SENA.FlightManagementSystem.Parameterization.Entity.AircraftType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="aircraft", schema = "aircraft_management")
@Schema(name = "aircraft_management", description = "Entidad que representa la gestión de aeronaves")
public class Arcraft extends ABaseEntity{
    
@Schema(description = "Fabricante de la aeronave", example = "Airbus")
    @Column(name = "manufacturer", nullable = false, length = 100)
    private String manufacturer;

    @Schema(description = "Matrícula/registro de la aeronave", example = "HK-3205")
    @Column(name = "registration_code", nullable = false, length = 50, unique = true)
    private String registrationCode;

    @Schema(description = "Horas de uso acumuladas", example = "15200")
    @Column(name = "hours_in_use", nullable = false)
    private Integer hoursInUse;

    // Relación con AircraftType (de Parameterization)
    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft_type_id", referencedColumnName = "id", nullable = false)
    private AircraftType aircraftType;

    // Getters y setters
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getRegistrationCode() { return registrationCode; }
    public void setRegistrationCode(String registrationCode) { this.registrationCode = registrationCode; }

    public Integer getHoursInUse() { return hoursInUse; }
    public void setHoursInUse(Integer hoursInUse) { this.hoursInUse = hoursInUse; }

    public AircraftType getAircraftType() { return aircraftType; }
    public void setAircraftType(AircraftType aircraftType) { this.aircraftType = aircraftType; }
}
