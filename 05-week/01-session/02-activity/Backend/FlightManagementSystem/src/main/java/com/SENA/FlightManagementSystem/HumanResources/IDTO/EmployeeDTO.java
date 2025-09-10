package com.SENA.FlightManagementSystem.HumanResources.IDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EmployeeDTO", description = "DTO para transferir datos de empleado")
public class EmployeeDTO {

    @Schema(description = "Identificador único del empleado", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Código del empleado", example = "EMP001")
    private String code;

    @Schema(description = "Descripción del empleado", example = "Piloto senior con 10 años de experiencia")
    private String description;

    @Schema(description = "Salario del empleado", example = "2500000.00")
    private BigDecimal salary;

    @Schema(description = "Fecha de contratación", example = "2023-01-15")
    private LocalDate hireDate;

    @Schema(description = "ID del rol de tripulación", example = "123e4567-e89b-12d3-a456-426614174001")
    private String crewRoleId;

    @Schema(description = "Nombre del rol de tripulación", example = "Piloto")
    private String crewRoleName;

    @Schema(description = "Código del rol de tripulación", example = "PIL")
    private String crewRoleCode;

    @Schema(description = "ID de la persona asociada", example = "123e4567-e89b-12d3-a456-426614174002")
    private String personId;

    @Schema(description = "Nombre completo de la persona", example = "Juan Carlos Pérez García")
    private String personFullName;

    @Schema(description = "Número de documento", example = "12345678")
    private String personDocumentNumber;

    @Schema(description = "Email de la persona", example = "juan.perez@email.com")
    private String personEmail;

    @Schema(description = "Teléfono de la persona", example = "3001234567")
    private String personPhone;

    @Schema(description = "Años de servicio", example = "5")
    private Integer yearsOfService;

    @Schema(description = "Estado del empleado", example = "true")
    private Boolean status;

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(String id, String code, String description, BigDecimal salary, LocalDate hireDate,
                       String crewRoleId, String crewRoleName, String crewRoleCode,
                       String personId, String personFullName, String personDocumentNumber,
                       String personEmail, String personPhone, Integer yearsOfService, Boolean status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.salary = salary;
        this.hireDate = hireDate;
        this.crewRoleId = crewRoleId;
        this.crewRoleName = crewRoleName;
        this.crewRoleCode = crewRoleCode;
        this.personId = personId;
        this.personFullName = personFullName;
        this.personDocumentNumber = personDocumentNumber;
        this.personEmail = personEmail;
        this.personPhone = personPhone;
        this.yearsOfService = yearsOfService;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getCrewRoleId() {
        return crewRoleId;
    }

    public void setCrewRoleId(String crewRoleId) {
        this.crewRoleId = crewRoleId;
    }

    public String getCrewRoleName() {
        return crewRoleName;
    }

    public void setCrewRoleName(String crewRoleName) {
        this.crewRoleName = crewRoleName;
    }

    public String getCrewRoleCode() {
        return crewRoleCode;
    }

    public void setCrewRoleCode(String crewRoleCode) {
        this.crewRoleCode = crewRoleCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonFullName() {
        return personFullName;
    }

    public void setPersonFullName(String personFullName) {
        this.personFullName = personFullName;
    }

    public String getPersonDocumentNumber() {
        return personDocumentNumber;
    }

    public void setPersonDocumentNumber(String personDocumentNumber) {
        this.personDocumentNumber = personDocumentNumber;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public Integer getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(Integer yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}