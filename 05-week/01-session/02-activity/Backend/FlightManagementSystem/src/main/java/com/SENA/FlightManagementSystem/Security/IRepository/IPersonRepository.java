package com.SENA.FlightManagementSystem.Security.IRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Security.Entity.Person;

/**
 * Repository interface for Person entity.
 */
@Repository
public interface IPersonRepository extends IBaseRepository<Person, String> {
    
    /**
     * Find person by document number.
     */
    @Query("SELECT p FROM Person p WHERE p.documentNumber = :documentNumber AND p.status = true AND p.deletedAt IS NULL")
    Optional<Person> findByDocumentNumber(@Param("documentNumber") String documentNumber);
    
    /**
     * Find person by email.
     */
    @Query("SELECT p FROM Person p WHERE p.email = :email AND p.status = true AND p.deletedAt IS NULL")
    Optional<Person> findByEmail(@Param("email") String email);
    
    /**
     * Check if email exists (excluding specific person).
     */
    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.email = :email AND p.id != :personId AND p.status = true AND p.deletedAt IS NULL")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("personId") String personId);
    
    /**
     * Check if document number exists (excluding specific person).
     */
    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.documentNumber = :documentNumber AND p.id != :personId AND p.status = true AND p.deletedAt IS NULL")
    boolean existsByDocumentNumberAndIdNot(@Param("documentNumber") String documentNumber, @Param("personId") String personId);
}