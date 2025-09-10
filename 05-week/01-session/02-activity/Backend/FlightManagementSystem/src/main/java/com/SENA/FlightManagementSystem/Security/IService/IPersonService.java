package com.SENA.FlightManagementSystem.Security.IService;

import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Person;

/**
 * Service interface for Person entity.
 */
public interface IPersonService extends IBaseService<Person> {
    
    /**
     * Find person by document number.
     */
    Optional<Person> findByDocumentNumber(String documentNumber) throws Exception;
    
    /**
     * Find person by email.
     */
    Optional<Person> findByEmail(String email) throws Exception;
    
    /**
     * Validate person data before save/update.
     */
    void validatePersonData(Person person) throws Exception;
    
    /**
     * Check if email is already in use by another person.
     */
    boolean isEmailInUse(String email, String excludePersonId) throws Exception;
    
    /**
     * Check if document number is already in use by another person.
     */
    boolean isDocumentNumberInUse(String documentNumber, String excludePersonId) throws Exception;
}