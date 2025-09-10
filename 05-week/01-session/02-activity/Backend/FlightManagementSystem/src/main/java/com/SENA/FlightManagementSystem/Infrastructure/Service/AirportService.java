package com.SENA.FlightManagementSystem.Infrastructure.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.Airport;
import com.SENA.FlightManagementSystem.Infrastructure.IRepository.IAirportRepository;
import com.SENA.FlightManagementSystem.Infrastructure.IService.IAirportService;
import com.SENA.FlightManagementSystem.Geolocation.IService.ICityService;

@Service
public class AirportService extends ABaseService<Airport> implements IAirportService {

    @Autowired
    private IAirportRepository repository;

    @Autowired
    private ICityService cityService;

    @Override
    protected IBaseRepository<Airport, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<Airport> findByName(String name) throws Exception {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("El nombre del aeropuerto no puede estar vacío");
            }
            return repository.findByName(name.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar aeropuerto por nombre: " + e.getMessage());
        }
    }

    @Override
    public Optional<Airport> findByCode(String code) throws Exception {
        try {
            if (code == null || code.trim().isEmpty()) {
                throw new Exception("El código del aeropuerto no puede estar vacío");
            }
            return repository.findByCode(code.trim().toUpperCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar aeropuerto por código: " + e.getMessage());
        }
    }

    @Override
    public List<Airport> findByCityId(String cityId) throws Exception {
        try {
            if (cityId == null || cityId.trim().isEmpty()) {
                throw new Exception("El ID de la ciudad no puede estar vacío");
            }
            return repository.findByCityId(cityId);
        } catch (Exception e) {
            throw new Exception("Error al buscar aeropuertos por ciudad: " + e.getMessage());
        }
    }

    @Override
    public void validateAirportData(Airport airport) throws Exception {
        if (airport == null) {
            throw new Exception("Los datos del aeropuerto no pueden estar vacíos");
        }

        // Validar campos obligatorios heredados de ABaseEntity
        if (airport.getName() == null || airport.getName().trim().isEmpty()) {
            throw new Exception("El nombre del aeropuerto es obligatorio");
        }
        if (airport.getCode() == null || airport.getCode().trim().isEmpty()) {
            throw new Exception("El código del aeropuerto es obligatorio");
        }
        if (airport.getDescription() == null || airport.getDescription().trim().isEmpty()) {
            throw new Exception("La descripción del aeropuerto es obligatoria");
        }
        if (airport.getCity() == null) {
            throw new Exception("La ciudad del aeropuerto es obligatoria");
        }

        // Validar que la ciudad exista
        if (cityService.findById(airport.getCity().getId()).isEmpty()) {
            throw new Exception("La ciudad especificada no existe");
        }

        // Validar longitudes
        if (airport.getName().length() > 100) {
            throw new Exception("El nombre del aeropuerto no puede tener más de 100 caracteres");
        }
        if (airport.getCode().length() > 10) {
            throw new Exception("El código del aeropuerto no puede tener más de 10 caracteres");
        }
        if (airport.getDescription().length() > 255) {
            throw new Exception("La descripción del aeropuerto no puede tener más de 255 caracteres");
        }
        if (airport.getAddress() != null && airport.getAddress().length() > 255) {
            throw new Exception("La dirección del aeropuerto no puede tener más de 255 caracteres");
        }

        // Validar formato del código (solo letras mayúsculas y números)
        if (!airport.getCode().matches("^[A-Z0-9]+$")) {
            throw new Exception("El código del aeropuerto solo puede contener letras mayúsculas y números");
        }

        // Validar unicidad
        String excludeId = airport.getId() != null ? airport.getId() : "";
        if (isNameInUse(airport.getName(), excludeId)) {
            throw new Exception("Ya existe un aeropuerto con este nombre");
        }
        if (isCodeInUse(airport.getCode(), excludeId)) {
            throw new Exception("Ya existe un aeropuerto con este código");
        }
    }

    @Override
    public boolean isNameInUse(String name, String excludeAirportId) throws Exception {
        try {
            excludeAirportId = excludeAirportId != null ? excludeAirportId : "";
            return repository.existsByNameAndIdNot(name.trim(), excludeAirportId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el nombre del aeropuerto está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUse(String code, String excludeAirportId) throws Exception {
        try {
            excludeAirportId = excludeAirportId != null ? excludeAirportId : "";
            return repository.existsByCodeAndIdNot(code.trim().toUpperCase(), excludeAirportId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código del aeropuerto está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUseInCity(String code, String cityId, String excludeAirportId) throws Exception {
        try {
            excludeAirportId = excludeAirportId != null ? excludeAirportId : "";
            return repository.existsByCodeAndCityIdAndIdNot(code.trim().toUpperCase(), cityId, excludeAirportId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código del aeropuerto está en uso en la ciudad: " + e.getMessage());
        }
    }

    @Override
    public Airport save(Airport entity) throws Exception {
        try {
            // Normalizar datos antes de guardar
            if (entity.getName() != null) {
                entity.setName(entity.getName().trim());
            }
            if (entity.getCode() != null) {
                entity.setCode(entity.getCode().trim().toUpperCase());
            }
            if (entity.getDescription() != null) {
                entity.setDescription(entity.getDescription().trim());
            }
            if (entity.getAddress() != null) {
                entity.setAddress(entity.getAddress().trim());
            }

            // Validar datos
            validateAirportData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar el aeropuerto: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, Airport entity) throws Exception {
        try {
            // Normalizar datos antes de actualizar
            if (entity.getName() != null) {
                entity.setName(entity.getName().trim());
            }
            if (entity.getCode() != null) {
                entity.setCode(entity.getCode().trim().toUpperCase());
            }
            if (entity.getDescription() != null) {
                entity.setDescription(entity.getDescription().trim());
            }
            if (entity.getAddress() != null) {
                entity.setAddress(entity.getAddress().trim());
            }

            entity.setId(id);
            validateAirportData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el aeropuerto: " + e.getMessage());
        }
    }
}