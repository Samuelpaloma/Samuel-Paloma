package com.SENA.FlightManagementSystem.Infrastructure.IDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TerminalDTO", description = "DTO para transferir datos de terminal")
public class TerminalDTO {

    @Schema(description = "Identificador único de la terminal", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Código de la terminal", example = "T1")
    private String code;

    @Schema(description = "Nombre de la terminal", example = "Terminal Internacional")
    private String name;

    @Schema(description = "Descripción de la terminal", example = "Terminal principal para vuelos internacionales")
    private String description;

    @Schema(description = "ID del aeropuerto al que pertenece", example = "123e4567-e89b-12d3-a456-426614174001")
    private String airportId;

    @Schema(description = "Nombre del aeropuerto", example = "Aeropuerto Internacional El Dorado")
    private String airportName;

    @Schema(description = "Código del aeropuerto", example = "BOG")
    private String airportCode;

    @Schema(description = "Estado de la terminal", example = "true")
    private Boolean status;

    // Constructors
    public TerminalDTO() {}

    public TerminalDTO(String id, String code, String name, String description, 
                       String airportId, String airportName, String airportCode, Boolean status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}