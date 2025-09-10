package com.SENA.FlightManagementSystem.HumanResources.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;
import com.SENA.FlightManagementSystem.Parameterization.Entity.CrewRole;
import com.SENA.FlightManagementSystem.Security.Entity.Person;

@Entity
@Table(name = "employee", schema = "human_resources")
@Schema(name = "employee", description = "Entidad que representa un empleado de la aerolínea")
public class Employee extends ABaseEntity {

    @Schema(description = "Salario del empleado", example = "2500000.00")
    @Column(name = "salary", nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    @Schema(description = "Fecha de contratación", example = "2023-01-15")
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Schema(description = "Rol de tripulación del empleado")
    @ManyToOne
    @JoinColumn(name = "crew_role_id", nullable = false)
    private CrewRole crewRole;

    @Schema(description = "Persona asociada al empleado")
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // Constructors
    public Employee() {}

    public Employee(BigDecimal salary, LocalDate hireDate, CrewRole crewRole, Person person) {
        this.salary = salary;
        this.hireDate = hireDate;
        this.crewRole = crewRole;
        this.person = person;
    }

    // Getters and Setters
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

    public CrewRole getCrewRole() {
        return crewRole;
    }

    public void setCrewRole(CrewRole crewRole) {
        this.crewRole = crewRole;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Helper methods
    public String getFullName() {
        return person != null ? person.getFullName() : "";
    }

    public String getEmployeeCode() {
        StringBuilder code = new StringBuilder();
        if (getCode() != null && !getCode().trim().isEmpty()) {
            code.append(getCode());
        } else if (person != null && person.getDocumentNumber() != null) {
            code.append("EMP-").append(person.getDocumentNumber());
        }
        return code.toString();
    }

    public String getPositionInfo() {
        StringBuilder info = new StringBuilder();
        if (crewRole != null && crewRole.getName() != null) {
            info.append(crewRole.getName());
        }
        if (person != null && person.getFullName() != null) {
            if (info.length() > 0) {
                info.append(" - ");
            }
            info.append(person.getFullName());
        }
        return info.toString();
    }

    public int getYearsOfService() {
        if (hireDate != null) {
            return LocalDate.now().getYear() - hireDate.getYear();
        }
        return 0;
    }
}