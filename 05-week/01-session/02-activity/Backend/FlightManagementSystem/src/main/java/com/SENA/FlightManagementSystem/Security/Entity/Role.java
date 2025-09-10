package com.SENA.FlightManagementSystem.Security.Entity;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

@Entity
@Table(name = "role", schema = "security")
@Schema(name = "role", description = "Entidad que representa los roles del sistema")
public class Role extends ABaseEntity {

    @Schema(description = "Permisos asociados al rol")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permission", 
        schema = "security",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    // Constructors
    public Role() {}

    public Role(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    // Getters and Setters
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    // Helper methods
    public void addPermission(Permission permission) {
        if (this.permissions != null) {
            this.permissions.add(permission);
        }
    }

    public void removePermission(Permission permission) {
        if (this.permissions != null) {
            this.permissions.remove(permission);
        }
    }

    public boolean hasPermission(String permissionName) {
        if (this.permissions == null) return false;
        return this.permissions.stream()
                .anyMatch(permission -> permission.getName().equals(permissionName));
    }
}