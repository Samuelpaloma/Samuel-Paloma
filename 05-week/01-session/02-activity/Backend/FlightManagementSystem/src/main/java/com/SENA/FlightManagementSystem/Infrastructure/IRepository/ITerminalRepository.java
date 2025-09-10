package com.SENA.FlightManagementSystem.Infrastructure.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Terminal;

/**
 * Repository interface for Terminal entity.
 */
@Repository
public interface ITerminalRepository extends IBaseRepository<Terminal, String> {
    
    /**
     * Find terminal by name.
     */
    @Query("SELECT t FROM Terminal t WHERE t.name = :name AND t.status = true AND t.deletedAt IS NULL")
    Optional<Terminal> findByName(@Param("name") String name);
    
    /**
     * Find terminal by code.
     */
    @Query("SELECT t FROM Terminal t WHERE t.code = :code AND t.status = true AND t.deletedAt IS NULL")
    Optional<Terminal> findByCode(@Param("code") String code);
    
    /**
     * Find terminals by airport.
     */
    @Query("SELECT t FROM Terminal t WHERE t.airport.id = :airportId AND t.status = true AND t.deletedAt IS NULL")
    List<Terminal> findByAirportId(@Param("airportId") String airportId);
    
    /**
     * Check if terminal name exists (excluding specific terminal).
     */
    @Query("SELECT COUNT(t) > 0 FROM Terminal t WHERE t.name = :name AND t.id != :terminalId AND t.status = true AND t.deletedAt IS NULL")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("terminalId") String terminalId);
    
    /**
     * Check if terminal code exists (excluding specific terminal).
     */
    @Query("SELECT COUNT(t) > 0 FROM Terminal t WHERE t.code = :code AND t.id != :terminalId AND t.status = true AND t.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("terminalId") String terminalId);
    
    /**
     * Check if terminal code exists in specific airport (excluding specific terminal).
     */
    @Query("SELECT COUNT(t) > 0 FROM Terminal t WHERE t.code = :code AND t.airport.id = :airportId AND t.id != :terminalId AND t.status = true AND t.deletedAt IS NULL")
    boolean existsByCodeAndAirportIdAndIdNot(@Param("code") String code, @Param("airportId") String airportId, @Param("terminalId") String terminalId);
    
    /**
     * Check if terminal name exists in specific airport (excluding specific terminal).
     */
    @Query("SELECT COUNT(t) > 0 FROM Terminal t WHERE t.name = :name AND t.airport.id = :airportId AND t.id != :terminalId AND t.status = true AND t.deletedAt IS NULL")
    boolean existsByNameAndAirportIdAndIdNot(@Param("name") String name, @Param("airportId") String airportId, @Param("terminalId") String terminalId);
}