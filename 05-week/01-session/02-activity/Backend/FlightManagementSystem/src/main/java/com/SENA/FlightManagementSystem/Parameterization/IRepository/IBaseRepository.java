package com.SENA.FlightManagementSystem.Parameterization.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.SENA.FlightManagementSystem.Parameterization.Entity.ABaseEntity;

/**
 * Base repository interface providing common database operations for entities.
 * 
 * @param <T> The type of entity extending ABaseEntity.
 * @param <ID> The type of the entity's primary key.
 */
@NoRepositoryBean
public interface IBaseRepository<T extends ABaseEntity, ID> extends JpaRepository<T, ID> {
    
    /**
     * Retrieves all entities with status set to true and deletedAt is null.
     * 
     * @return List of active entities.
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.status = true AND e.deletedAt IS NULL")
    List<T> findByStatusTrueAndDeletedAtIsNull();
    
    /**
     * Retrieves all entities with status set to true.
     * 
     * @return List of entities with status = true.
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.status = true")
    List<T> findByStatusTrue();
}