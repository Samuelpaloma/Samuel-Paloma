package com.SENA.FlightManagementSystem.Security.IService;

import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;

/**
 * Service interface for Permission entity.
 */
public interface IPermissionService extends IBaseService<Permission> {
    
    /**
     * Find permission by name.
     */
    Optional<Permission> findByName(String name) throws Exception;
    
    /**
     * Find permission by code.
     */
    Optional<Permission> findByCode(String code) throws Exception;
    
    /**
     * Validate permission data before save/update.
     */
    void validatePermissionData(Permission permission) throws Exception;
    
    /**
     * Check if permission name is already in use by another permission.
     */
    boolean isNameInUse(String name, String excludePermissionId) throws Exception;
    
    /**
     * Check if permission code is already in use by another permission.
     */
    boolean isCodeInUse(String code, String excludePermissionId) throws Exception;
}