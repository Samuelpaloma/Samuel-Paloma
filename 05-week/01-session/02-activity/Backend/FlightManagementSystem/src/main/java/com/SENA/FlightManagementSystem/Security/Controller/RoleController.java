package com.SENA.FlightManagementSystem.Security.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.Parameterization.Controller.ABaseController;
import com.SENA.FlightManagementSystem.Parameterization.DTO.ApiResponseDto;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;
import com.SENA.FlightManagementSystem.Security.Entity.Role;
import com.SENA.FlightManagementSystem.Security.IService.IRoleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/role")
public class RoleController extends ABaseController<Role, IRoleService> {

    public RoleController(IRoleService service) {
        super(service, "Role");
    }

    /**
     * Find role by name.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<Optional<Role>>> findByName(@PathVariable String name) {
        try {
            Optional<Role> role = service.findByName(name);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol encontrado", role, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find role by code.
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponseDto<Optional<Role>>> findByCode(@PathVariable String code) {
        try {
            Optional<Role> role = service.findByCode(code);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol encontrado", role, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get all permissions for a role.
     */
    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<ApiResponseDto<List<Permission>>> getRolePermissions(@PathVariable String roleId) {
        try {
            List<Permission> permissions = service.getRolePermissions(roleId);
            return ResponseEntity.ok(new ApiResponseDto<>("Permisos del rol obtenidos", permissions, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Assign permissions to a role.
     */
    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<ApiResponseDto<String>> assignPermissions(@PathVariable String roleId, 
                                                                   @RequestBody List<String> permissionIds) {
        try {
            service.assignPermissions(roleId, permissionIds);
            return ResponseEntity.ok(new ApiResponseDto<>("Permisos asignados correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Remove permissions from a role.
     */
    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<ApiResponseDto<String>> removePermissions(@PathVariable String roleId, 
                                                                   @RequestBody List<String> permissionIds) {
        try {
            service.removePermissions(roleId, permissionIds);
            return ResponseEntity.ok(new ApiResponseDto<>("Permisos removidos correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Add single permission to role.
     */
    @PostMapping("/{roleId}/permission/{permissionId}")
    public ResponseEntity<ApiResponseDto<String>> addPermission(@PathVariable String roleId, 
                                                               @PathVariable String permissionId) {
        try {
            service.addPermission(roleId, permissionId);
            return ResponseEntity.ok(new ApiResponseDto<>("Permiso agregado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Remove single permission from role.
     */
    @DeleteMapping("/{roleId}/permission/{permissionId}")
    public ResponseEntity<ApiResponseDto<String>> removePermission(@PathVariable String roleId, 
                                                                  @PathVariable String permissionId) {
        try {
            service.removePermission(roleId, permissionId);
            return ResponseEntity.ok(new ApiResponseDto<>("Permiso removido correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Check if role has specific permission.
     */
    @GetMapping("/{roleId}/permission/{permissionName}/check")
    public ResponseEntity<ApiResponseDto<Boolean>> hasPermission(@PathVariable String roleId, 
                                                                @PathVariable String permissionName) {
        try {
            boolean hasPermission = service.hasPermission(roleId, permissionName);
            return ResponseEntity.ok(new ApiResponseDto<>("Verificación completada", hasPermission, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), false, false));
        }
    }
}