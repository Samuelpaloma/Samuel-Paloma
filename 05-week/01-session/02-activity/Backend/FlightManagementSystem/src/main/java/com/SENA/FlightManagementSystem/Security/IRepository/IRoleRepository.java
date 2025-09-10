package com.SENA.FlightManagementSystem.Security.IRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Security.Entity.Role;

/**
 * Repository interface for Role entity.
 */
@Repository
public interface IRoleRepository extends IBaseRepository<Role, String> {
    
    /**
     * Find role by name.
     */
    @Query("SELECT r FROM Role r WHERE r.name = :name AND r.status = true AND r.deletedAt IS NULL")
    Optional<Role> findByName(@Param("name") String name);
    
    /**
     * Find role by code.
     */
    @Query("SELECT r FROM Role r WHERE r.code = :code AND r.status = true AND r.deletedAt IS NULL")
    Optional<Role> findByCode(@Param("code") String code);
    
    /**
     * Check if role name exists (excluding specific role).
     */
    @Query("SELECT COUNT(r) > 0 FROM Role r WHERE r.name = :name AND r.id != :roleId AND r.status = true AND r.deletedAt IS NULL")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("roleId") String roleId);
    
    /**
     * Check if role code exists (excluding specific role).
     */
    @Query("SELECT COUNT(r) > 0 FROM Role r WHERE r.code = :code AND r.id != :roleId AND r.status = true AND r.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("roleId") String roleId);
}