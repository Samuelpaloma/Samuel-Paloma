package com.SENA.FlightManagementSystem.Infrastructure.IService;

import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.BoardingGate;

/**
 * Service interface for BoardingGate entity.
 */
public interface IBoardingGateService extends IBaseService<BoardingGate> {
    
    /**
     * Find boarding gate by name.
     */
    Optional<BoardingGate> findByName(String name) throws Exception;
    
    /**
     * Find boarding gate by code.
     */
    Optional<BoardingGate> findByCode(String code) throws Exception;
    
    /**
     * Find boarding gates by terminal.
     */
    List<BoardingGate> findByTerminalId(String terminalId) throws Exception;
    
    /**
     * Find boarding gates by airport (through terminal).
     */
    List<BoardingGate> findByAirportId(String airportId) throws Exception;
    
    /**
     * Validate boarding gate data before save/update.
     */
    void validateBoardingGateData(BoardingGate boardingGate) throws Exception;
    
    /**
     * Check if boarding gate name is already in use by another boarding gate.
     */
    boolean isNameInUse(String name, String excludeBoardingGateId) throws Exception;
    
    /**
     * Check if boarding gate code is already in use by another boarding gate.
     */
    boolean isCodeInUse(String code, String excludeBoardingGateId) throws Exception;
    
    /**
     * Check if boarding gate code is already in use in specific terminal.
     */
    boolean isCodeInUseInTerminal(String code, String terminalId, String excludeBoardingGateId) throws Exception;
    
    /**
     * Check if boarding gate name is already in use in specific terminal.
     */
    boolean isNameInUseInTerminal(String name, String terminalId, String excludeBoardingGateId) throws Exception;
}