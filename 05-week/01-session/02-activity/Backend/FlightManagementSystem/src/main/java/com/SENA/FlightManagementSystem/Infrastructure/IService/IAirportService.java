package com.SENA.FlightManagementSystem.Infrastructure.IService;

import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Airport;

/**
 * Service interface for Airport entity.
 */
public interface IAirportService extends IBaseService<Airport> {
    
    /**
     * Find airport by name.
     */
    Optional<Airport> findByName(String name) throws Exception;
    
    /**
     * Find airport by code.
     */
    Optional<Airport> findByCode(String code) throws Exception;
    
    /**
     * Find airports by city.
     */
    List<Airport> findByCityId(String cityId) throws Exception;
    
    /**
     * Validate airport data before save/update.
     */
    void validateAirportData(Airport airport) throws Exception;
    
    /**
     * Check if airport name is already in use by another airport.
     */
    boolean isNameInUse(String name, String excludeAirportId) throws Exception;
    
    /**
     * Check if airport code is already in use by another airport.
     */
    boolean isCodeInUse(String code, String excludeAirportId) throws Exception;
    
    /**
     * Check if airport code is already in use in specific city.
     */
    boolean isCodeInUseInCity(String code, String cityId, String excludeAirportId) throws Exception;
}