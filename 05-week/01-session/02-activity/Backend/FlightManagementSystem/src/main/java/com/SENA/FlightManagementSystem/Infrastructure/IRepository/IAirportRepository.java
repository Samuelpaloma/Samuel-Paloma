package com.SENA.FlightManagementSystem.Infrastructure.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Airport;

/**
 * Repository interface for Airport entity.
 */
@Repository
public interface IAirportRepository extends IBaseRepository<Airport, String> {
    
    /**
     * Find airport by name.
     */
    @Query("SELECT a FROM Airport a WHERE a.name = :name AND a.status = true AND a.deletedAt IS NULL")
    Optional<Airport> findByName(@Param("name") String name);
    
    /**
     * Find airport by code.
     */
    @Query("SELECT a FROM Airport a WHERE a.code = :code AND a.status = true AND a.deletedAt IS NULL")
    Optional<Airport> findByCode(@Param("code") String code);
    
    /**
     * Find airports by city.
     */
    @Query("SELECT a FROM Airport a WHERE a.city.id = :cityId AND a.status = true AND a.deletedAt IS NULL")
    List<Airport> findByCityId(@Param("cityId") String cityId);
    
    /**
     * Check if airport name exists (excluding specific airport).
     */
    @Query("SELECT COUNT(a) > 0 FROM Airport a WHERE a.name = :name AND a.id != :airportId AND a.status = true AND a.deletedAt IS NULL")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("airportId") String airportId);
    
    /**
     * Check if airport code exists (excluding specific airport).
     */
    @Query("SELECT COUNT(a) > 0 FROM Airport a WHERE a.code = :code AND a.id != :airportId AND a.status = true AND a.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("airportId") String airportId);
    
    /**
     * Check if airport code exists in specific city (excluding specific airport).
     */
    @Query("SELECT COUNT(a) > 0 FROM Airport a WHERE a.code = :code AND a.city.id = :cityId AND a.id != :airportId AND a.status = true AND a.deletedAt IS NULL")
    boolean existsByCodeAndCityIdAndIdNot(@Param("code") String code, @Param("cityId") String cityId, @Param("airportId") String airportId);
}