package com.SENA.FlightManagementSystem.Security.IRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;

/**
 * Repository interface for Permission entity.
 */
@Repository
public interface IPermissionRepository extends IBaseRepository<Permission, String> {
    
    /**
     * Find permission by name.
     */
    @Query("SELECT p FROM Permission p WHERE p.name = :name AND p.status = true AND p.deletedAt IS NULL")
    Optional<Permission> findByName(@Param("name") String name);
    
    /**
     * Find permission by code.
     */
    @Query("SELECT p FROM Permission p WHERE p.code = :code AND p.status = true AND p.deletedAt IS NULL")
    Optional<Permission> findByCode(@Param("code") String code);
    
    /**
     * Check if permission name exists (excluding specific permission).
     */
    @Query("SELECT COUNT(p) > 0 FROM Permission p WHERE p.name = :name AND p.id != :permissionId AND p.status = true AND p.deletedAt IS NULL")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("permissionId") String permissionId);
    
    /**
     * Check if permission code exists (excluding specific permission).
     */
    @Query("SELECT COUNT(p) > 0 FROM Permission p WHERE p.code = :code AND p.id != :permissionId AND p.status = true AND p.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("permissionId") String permissionId);
}