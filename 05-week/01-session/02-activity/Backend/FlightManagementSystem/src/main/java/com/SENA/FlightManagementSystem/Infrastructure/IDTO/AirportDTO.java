package com.SENA.FlightManagementSystem.Infrastructure.IDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AirportDTO", description = "DTO para transferir datos de aeropuerto")
public class AirportDTO {

    @Schema(description = "Identificador único del aeropuerto", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Código del aeropuerto", example = "BOG")
    private String code;

    @Schema(description = "Nombre del aeropuerto", example = "Aeropuerto Internacional El Dorado")
    private String name;

    @Schema(description = "Descripción del aeropuerto", example = "Principal aeropuerto de Colombia")
    private String description;

    @Schema(description = "Dirección del aeropuerto", example = "Avenida El Dorado # 103-9")
    private String address;

    @Schema(description = "ID de la ciudad donde se encuentra", example = "123e4567-e89b-12d3-a456-426614174001")
    private String cityId;

    @Schema(description = "Nombre de la ciudad", example = "Bogotá")
    private String cityName;

    @Schema(description = "Estado del aeropuerto", example = "true")
    private Boolean status;

    // Constructors
    public AirportDTO() {}

    public AirportDTO(String id, String code, String name, String description, String address, 
                      String cityId, String cityName, Boolean status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.address = address;
        this.cityId = cityId;
        this.cityName = cityName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}