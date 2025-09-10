package com.SENA.FlightManagementSystem.Parameterization.IRepository;

import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.Entity.DocumentType;

/**
 * Repository interface for DocumentType entity.
 * Extends IBaseRepository to inherit common CRUD operations.
 */
@Repository
public interface IDocumentTypeRepository extends IBaseRepository<DocumentType, String> {
    
    // Aquí se pueden agregar métodos específicos para DocumentType si son necesarios
    // Por ejemplo:
    // Optional<DocumentType> findByCode(String code);
    // List<DocumentType> findByNameContaining(String name);
}