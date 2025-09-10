package com.SENA.FlightManagementSystem.Security.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;
import com.SENA.FlightManagementSystem.Parameterization.Entity.DocumentType;

@Entity
@Table(name = "person", schema = "security")
@Schema(name = "person", description = "Entidad que representa la información personal de una persona")
public class Person extends ABaseEntity {

    @Schema(description = "Primer nombre de la persona", example = "Juan")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Schema(description = "Segundo nombre de la persona", example = "Carlos")
    @Column(name = "second_name", nullable = true, length = 50)
    private String secondName;

    @Schema(description = "Primer apellido de la persona", example = "Pérez")
    @Column(name = "first_surname", nullable = false, length = 50)
    private String firstSurname;

    @Schema(description = "Segundo apellido de la persona", example = "García")
    @Column(name = "second_surname", nullable = true, length = 50)
    private String secondSurname;

    @Schema(description = "Número del documento de identidad", example = "12345678")
    @Column(name = "document_number", nullable = false, length = 20, unique = true)
    private String documentNumber;

    @Schema(description = "Dirección de residencia", example = "Calle 123 #45-67")
    @Column(name = "address", nullable = true, length = 255)
    private String address;

    @Schema(description = "Número de teléfono", example = "3001234567")
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Schema(description = "Correo electrónico", example = "juan.perez@email.com")
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Schema(description = "Tipo de documento asociado")
    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    // Constructors
    public Person() {}

    public Person(String firstName, String firstSurname, String documentNumber, String email) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.documentNumber = documentNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(firstName);
        if (secondName != null && !secondName.trim().isEmpty()) {
            fullName.append(" ").append(secondName);
        }
        fullName.append(" ").append(firstSurname);
        if (secondSurname != null && !secondSurname.trim().isEmpty()) {
            fullName.append(" ").append(secondSurname);
        }
        return fullName.toString();
    }
}