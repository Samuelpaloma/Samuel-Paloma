package com.SENA.FlightManagementSystem.Security.Entity;

import java.time.LocalDateTime;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

@Entity
@Table(name = "user", schema = "security")
@Schema(name = "user", description = "Entidad que representa los usuarios del sistema")
public class User extends ABaseEntity {

    @Schema(description = "Nombre de usuario único", example = "juan.perez")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Schema(description = "Contraseña encriptada del usuario")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Schema(description = "Indica si la cuenta está bloqueada", example = "false")
    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked = false;

    @Schema(description = "Número de intentos fallidos de login", example = "0")
    @Column(name = "failed_attempts", nullable = false)
    private Integer failedAttempts = 0;

    @Schema(description = "Fecha del último inicio de sesión")
    @Column(name = "last_login", nullable = true)
    private LocalDateTime lastLogin;

    @Schema(description = "Fecha de vencimiento de la cuenta")
    @Column(name = "account_expires_at", nullable = true)
    private LocalDateTime accountExpiresAt;

    @Schema(description = "Fecha de vencimiento de la contraseña")
    @Column(name = "password_expires_at", nullable = true)
    private LocalDateTime passwordExpiresAt;

    @Schema(description = "Persona asociada al usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Schema(description = "Roles asignados al usuario")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role", 
        schema = "security",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    // Constructors
    public User() {}

    public User(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.isActive = true;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getAccountExpiresAt() {
        return accountExpiresAt;
    }

    public void setAccountExpiresAt(LocalDateTime accountExpiresAt) {
        this.accountExpiresAt = accountExpiresAt;
    }

    public LocalDateTime getPasswordExpiresAt() {
        return passwordExpiresAt;
    }

    public void setPasswordExpiresAt(LocalDateTime passwordExpiresAt) {
        this.passwordExpiresAt = passwordExpiresAt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // Helper methods
    public void addRole(Role role) {
        if (this.roles != null) {
            this.roles.add(role);
        }
    }

    public void removeRole(Role role) {
        if (this.roles != null) {
            this.roles.remove(role);
        }
    }

    public boolean hasRole(String roleName) {
        if (this.roles == null) return false;
        return this.roles.stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    public boolean hasPermission(String permissionName) {
        if (this.roles == null) return false;
        return this.roles.stream()
                .anyMatch(role -> role.hasPermission(permissionName));
    }

    public boolean isAccountNonExpired() {
        return accountExpiresAt == null || accountExpiresAt.isAfter(LocalDateTime.now());
    }

    public boolean isCredentialsNonExpired() {
        return passwordExpiresAt == null || passwordExpiresAt.isAfter(LocalDateTime.now());
    }

    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    public boolean isEnabled() {
        return isActive && isAccountNonExpired() && isAccountNonLocked();
    }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
        if (this.failedAttempts >= 5) { // Bloquear después de 5 intentos fallidos
            this.isLocked = true;
        }
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
        this.isLocked = false;
    }
}