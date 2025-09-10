package com.SENA.FlightManagementSystem.Security.IService;

import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;
import com.SENA.FlightManagementSystem.Security.Entity.Role;

/**
 * Service interface for Role entity.
 */
public interface IRoleService extends IBaseService<Role> {
    
    /**
     * Find role by name.
     */
    Optional<Role> findByName(String name) throws Exception;
    
    /**
     * Find role by code.
     */
    Optional<Role> findByCode(String code) throws Exception;
    
    /**
     * Assign permissions to a role.
     */
    void assignPermissions(String roleId, List<String> permissionIds) throws Exception;
    
    /**
     * Remove permissions from a role.
     */
    void removePermissions(String roleId, List<String> permissionIds) throws Exception;
    
    /**
     * Add single permission to role.
     */
    void addPermission(String roleId, String permissionId) throws Exception;
    
    /**
     * Remove single permission from role.
     */
    void removePermission(String roleId, String permissionId) throws Exception;
    
    /**
     * Get all permissions for a role.
     */
    List<Permission> getRolePermissions(String roleId) throws Exception;
    
    /**
     * Check if role has specific permission.
     */
    boolean hasPermission(String roleId, String permissionName) throws Exception;
    
    /**
     * Validate role data before save/update.
     */
    void validateRoleData(Role role) throws Exception;
    
    /**
     * Check if role name is already in use by another role.
     */
    boolean isNameInUse(String name, String excludeRoleId) throws Exception;
    
    /**
     * Check if role code is already in use by another role.
     */
    boolean isCodeInUse(String code, String excludeRoleId) throws Exception;
}