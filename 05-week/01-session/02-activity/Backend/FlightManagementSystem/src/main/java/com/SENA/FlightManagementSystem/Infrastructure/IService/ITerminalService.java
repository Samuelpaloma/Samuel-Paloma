package com.SENA.FlightManagementSystem.Infrastructure.IService;

import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Terminal;

/**
 * Service interface for Terminal entity.
 */
public interface ITerminalService extends IBaseService<Terminal> {
    
    /**
     * Find terminal by name.
     */
    Optional<Terminal> findByName(String name) throws Exception;
    
    /**
     * Find terminal by code.
     */
    Optional<Terminal> findByCode(String code) throws Exception;
    
    /**
     * Find terminals by airport.
     */
    List<Terminal> findByAirportId(String airportId) throws Exception;
    
    /**
     * Validate terminal data before save/update.
     */
    void validateTerminalData(Terminal terminal) throws Exception;
    
    /**
     * Check if terminal name is already in use by another terminal.
     */
    boolean isNameInUse(String name, String excludeTerminalId) throws Exception;
    
    /**
     * Check if terminal code is already in use by another terminal.
     */
    boolean isCodeInUse(String code, String excludeTerminalId) throws Exception;
    
    /**
     * Check if terminal code is already in use in specific airport.
     */
    boolean isCodeInUseInAirport(String code, String airportId, String excludeTerminalId) throws Exception;
    
    /**
     * Check if terminal name is already in use in specific airport.
     */
    boolean isNameInUseInAirport(String name, String airportId, String excludeTerminalId) throws Exception;
}