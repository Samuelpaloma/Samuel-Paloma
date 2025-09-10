package com.SENA.FlightManagementSystem.Security.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.Parameterization.Controller.ABaseController;
import com.SENA.FlightManagementSystem.Parameterization.DTO.ApiResponseDto;
import com.SENA.FlightManagementSystem.Security.Entity.Role;
import com.SENA.FlightManagementSystem.Security.Entity.User;
import com.SENA.FlightManagementSystem.Security.IService.IUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/user")
public class UserController extends ABaseController<User, IUserService> {

    public UserController(IUserService service) {
        super(service, "User");
    }

    /**
     * Find user by username.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponseDto<Optional<User>>> findByUsername(@PathVariable String username) {
        try {
            Optional<User> user = service.findByUsername(username);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario encontrado", user, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find user by person email.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDto<Optional<User>>> findByPersonEmail(@PathVariable String email) {
        try {
            Optional<User> user = service.findByPersonEmail(email);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario encontrado", user, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find user by person document number.
     */
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<ApiResponseDto<Optional<User>>> findByPersonDocumentNumber(@PathVariable String documentNumber) {
        try {
            Optional<User> user = service.findByPersonDocumentNumber(documentNumber);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario encontrado", user, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get all active users.
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponseDto<List<User>>> findAllActiveUsers() {
        try {
            List<User> users = service.findAllActiveUsers();
            return ResponseEntity.ok(new ApiResponseDto<>("Usuarios activos obtenidos", users, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get users by role name.
     */
    @GetMapping("/role/{roleName}")
    public ResponseEntity<ApiResponseDto<List<User>>> findByRoleName(@PathVariable String roleName) {
        try {
            List<User> users = service.findByRoleName(roleName);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuarios por rol obtenidos", users, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get locked users.
     */
    @GetMapping("/locked")
    public ResponseEntity<ApiResponseDto<List<User>>> findLockedUsers() {
        try {
            List<User> users = service.findLockedUsers();
            return ResponseEntity.ok(new ApiResponseDto<>("Usuarios bloqueados obtenidos", users, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Create new user with person and roles.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<User>> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = service.createUser(request.getUsername(), request.getPassword(), 
                                         request.getPersonId(), request.getRoleIds());
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario creado correctamente", user, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Change user password.
     */
    @PutMapping("/{userId}/change-password")
    public ResponseEntity<ApiResponseDto<String>> changePassword(@PathVariable String userId, 
                                                                @RequestBody ChangePasswordRequest request) {
        try {
            service.changePassword(userId, request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok(new ApiResponseDto<>("Contraseña cambiada correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Reset user password (admin function).
     */
    @PutMapping("/{userId}/reset-password")
    public ResponseEntity<ApiResponseDto<String>> resetPassword(@PathVariable String userId, 
                                                               @RequestBody ResetPasswordRequest request) {
        try {
            service.resetPassword(userId, request.getNewPassword());
            return ResponseEntity.ok(new ApiResponseDto<>("Contraseña reseteada correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Lock user account.
     */
    @PutMapping("/{userId}/lock")
    public ResponseEntity<ApiResponseDto<String>> lockUser(@PathVariable String userId) {
        try {
            service.lockUser(userId);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario bloqueado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Unlock user account.
     */
    @PutMapping("/{userId}/unlock")
    public ResponseEntity<ApiResponseDto<String>> unlockUser(@PathVariable String userId) {
        try {
            service.unlockUser(userId);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario desbloqueado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Activate user account.
     */
    @PutMapping("/{userId}/activate")
    public ResponseEntity<ApiResponseDto<String>> activateUser(@PathVariable String userId) {
        try {
            service.activateUser(userId);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario activado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Deactivate user account.
     */
    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponseDto<String>> deactivateUser(@PathVariable String userId) {
        try {
            service.deactivateUser(userId);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario desactivado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get all roles for a user.
     */
    @GetMapping("/{userId}/roles")
    public ResponseEntity<ApiResponseDto<List<Role>>> getUserRoles(@PathVariable String userId) {
        try {
            List<Role> roles = service.getUserRoles(userId);
            return ResponseEntity.ok(new ApiResponseDto<>("Roles del usuario obtenidos", roles, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Assign roles to user.
     */
    @PostMapping("/{userId}/roles")
    public ResponseEntity<ApiResponseDto<String>> assignRoles(@PathVariable String userId, 
                                                             @RequestBody List<String> roleIds) {
        try {
            service.assignRoles(userId, roleIds);
            return ResponseEntity.ok(new ApiResponseDto<>("Roles asignados correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Remove roles from user.
     */
    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<ApiResponseDto<String>> removeRoles(@PathVariable String userId, 
                                                             @RequestBody List<String> roleIds) {
        try {
            service.removeRoles(userId, roleIds);
            return ResponseEntity.ok(new ApiResponseDto<>("Roles removidos correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Add single role to user.
     */
    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<ApiResponseDto<String>> addRole(@PathVariable String userId, 
                                                         @PathVariable String roleId) {
        try {
            service.addRole(userId, roleId);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol agregado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Remove single role from user.
     */
    @DeleteMapping("/{userId}/role/{roleId}")
    public ResponseEntity<ApiResponseDto<String>> removeRole(@PathVariable String userId, 
                                                            @PathVariable String roleId) {
        try {
            service.removeRole(userId, roleId);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol removido correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Check if user has specific role.
     */
    @GetMapping("/{userId}/role/{roleName}/check")
    public ResponseEntity<ApiResponseDto<Boolean>> hasRole(@PathVariable String userId, 
                                                          @PathVariable String roleName) {
        try {
            boolean hasRole = service.hasRole(userId, roleName);
            return ResponseEntity.ok(new ApiResponseDto<>("Verificación completada", hasRole, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), false, false));
        }
    }

    /**
     * Check if user has specific permission.
     */
    @GetMapping("/{userId}/permission/{permissionName}/check")
    public ResponseEntity<ApiResponseDto<Boolean>> hasPermission(@PathVariable String userId, 
                                                                @PathVariable String permissionName) {
        try {
            boolean hasPermission = service.hasPermission(userId, permissionName);
            return ResponseEntity.ok(new ApiResponseDto<>("Verificación completada", hasPermission, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), false, false));
        }
    }

    // DTOs for request bodies
    public static class CreateUserRequest {
        private String username;
        private String password;
        private String personId;
        private List<String> roleIds;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getPersonId() { return personId; }
        public void setPersonId(String personId) { this.personId = personId; }
        public List<String> getRoleIds() { return roleIds; }
        public void setRoleIds(List<String> roleIds) { this.roleIds = roleIds; }
    }

    public static class ChangePasswordRequest {
        private String oldPassword;
        private String newPassword;

        // Getters and setters
        public String getOldPassword() { return oldPassword; }
        public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }

    public static class ResetPasswordRequest {
        private String newPassword;

        // Getters and setters
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}