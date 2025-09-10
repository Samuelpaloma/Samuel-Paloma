package com.SENA.FlightManagementSystem.Security.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

@Entity
@Table(name = "permission", schema = "security")
@Schema(name = "permission", description = "Entidad que representa los permisos del sistema")
public class Permission extends ABaseEntity {

    // Constructors
    public Permission() {}

    public Permission(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    // Los campos name, description, code ya están en ABaseEntity
    // No necesitamos agregar campos adicionales por ahora
    
    // Si necesitas campos adicionales específicos para permisos:
    /*
    @Schema(description = "Módulo al que pertenece el permiso", example = "USER_MANAGEMENT")
    @Column(name = "module", nullable = true, length = 50)
    private String module;

    @Schema(description = "Acción del permiso", example = "CREATE")
    @Column(name = "action", nullable = true, length = 50)
    private String action;
    */
}