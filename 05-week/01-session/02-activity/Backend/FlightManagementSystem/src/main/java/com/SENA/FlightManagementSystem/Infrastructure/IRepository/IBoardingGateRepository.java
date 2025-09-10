package com.SENA.FlightManagementSystem.Infrastructure.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.BoardingGate;

/**
 * Repository interface for BoardingGate entity.
 */
@Repository
public interface IBoardingGateRepository extends IBaseRepository<BoardingGate, String> {
    
    /**
     * Find boarding gate by name.
     */
    @Query("SELECT bg FROM BoardingGate bg WHERE bg.name = :name AND bg.status = true AND bg.deletedAt IS NULL")
    Optional<BoardingGate> findByName(@Param("name") String name);
    
    /**
     * Find boarding gate by code.
     */
    @Query("SELECT bg FROM BoardingGate bg WHERE bg.code = :code AND bg.status = true AND bg.deletedAt IS NULL")
    Optional<BoardingGate> findByCode(@Param("code") String code);
    
    /**
     * Find boarding gates by terminal.
     */
    @Query("SELECT bg FROM BoardingGate bg WHERE bg.terminal.id = :terminalId AND bg.status = true AND bg.deletedAt IS NULL")
    List<BoardingGate> findByTerminalId(@Param("terminalId") String terminalId);
    
    /**
     * Find boarding gates by airport (through terminal).
     */
    @Query("SELECT bg FROM BoardingGate bg WHERE bg.terminal.airport.id = :airportId AND bg.status = true AND bg.deletedAt IS NULL")
    List<BoardingGate> findByAirportId(@Param("airportId") String airportId);
    
    /**
     * Check if boarding gate name exists (excluding specific boarding gate).
     */
    @Query("SELECT COUNT(bg) > 0 FROM BoardingGate bg WHERE bg.name = :name AND bg.id != :boardingGateId AND bg.status = true AND bg.deletedAt IS NULL")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("boardingGateId") String boardingGateId);
    
    /**
     * Check if boarding gate code exists (excluding specific boarding gate).
     */
    @Query("SELECT COUNT(bg) > 0 FROM BoardingGate bg WHERE bg.code = :code AND bg.id != :boardingGateId AND bg.status = true AND bg.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("boardingGateId") String boardingGateId);
    
    /**
     * Check if boarding gate code exists in specific terminal (excluding specific boarding gate).
     */
    @Query("SELECT COUNT(bg) > 0 FROM BoardingGate bg WHERE bg.code = :code AND bg.terminal.id = :terminalId AND bg.id != :boardingGateId AND bg.status = true AND bg.deletedAt IS NULL")
    boolean existsByCodeAndTerminalIdAndIdNot(@Param("code") String code, @Param("terminalId") String terminalId, @Param("boardingGateId") String boardingGateId);
    
    /**
     * Check if boarding gate name exists in specific terminal (excluding specific boarding gate).
     */
    @Query("SELECT COUNT(bg) > 0 FROM BoardingGate bg WHERE bg.name = :name AND bg.terminal.id = :terminalId AND bg.id != :boardingGateId AND bg.status = true AND bg.deletedAt IS NULL")
    boolean existsByNameAndTerminalIdAndIdNot(@Param("name") String name, @Param("terminalId") String terminalId, @Param("boardingGateId") String boardingGateId);
}