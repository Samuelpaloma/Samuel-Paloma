package com.SENA.FlightManagementSystem.Infrastructure.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Infrastructure.Entity.BoardingGate;
import com.SENA.FlightManagementSystem.Infrastructure.IRepository.IBoardingGateRepository;
import com.SENA.FlightManagementSystem.Infrastructure.IService.IBoardingGateService;
import com.SENA.FlightManagementSystem.Infrastructure.IService.ITerminalService;

@Service
public class BoardingGateService extends ABaseService<BoardingGate> implements IBoardingGateService {

    @Autowired
    private IBoardingGateRepository repository;

    @Autowired
    private ITerminalService terminalService;

    @Override
    protected IBaseRepository<BoardingGate, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<BoardingGate> findByName(String name) throws Exception {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("El nombre de la puerta de embarque no puede estar vacío");
            }
            return repository.findByName(name.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar puerta de embarque por nombre: " + e.getMessage());
        }
    }

    @Override
    public Optional<BoardingGate> findByCode(String code) throws Exception {
        try {
            if (code == null || code.trim().isEmpty()) {
                throw new Exception("El código de la puerta de embarque no puede estar vacío");
            }
            return repository.findByCode(code.trim().toUpperCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar puerta de embarque por código: " + e.getMessage());
        }
    }

    @Override
    public List<BoardingGate> findByTerminalId(String terminalId) throws Exception {
        try {
            if (terminalId == null || terminalId.trim().isEmpty()) {
                throw new Exception("El ID de la terminal no puede estar vacío");
            }
            return repository.findByTerminalId(terminalId);
        } catch (Exception e) {
            throw new Exception("Error al buscar puertas de embarque por terminal: " + e.getMessage());
        }
    }

    @Override
    public List<BoardingGate> findByAirportId(String airportId) throws Exception {
        try {
            if (airportId == null || airportId.trim().isEmpty()) {
                throw new Exception("El ID del aeropuerto no puede estar vacío");
            }
            return repository.findByAirportId(airportId);
        } catch (Exception e) {
            throw new Exception("Error al buscar puertas de embarque por aeropuerto: " + e.getMessage());
        }
    }

    @Override
    public void validateBoardingGateData(BoardingGate boardingGate) throws Exception {
        if (boardingGate == null) {
            throw new Exception("Los datos de la puerta de embarque no pueden estar vacíos");
        }

        // Validar campos obligatorios heredados de ABaseEntity
        if (boardingGate.getName() == null || boardingGate.getName().trim().isEmpty()) {
            throw new Exception("El nombre de la puerta de embarque es obligatorio");
        }
        if (boardingGate.getCode() == null || boardingGate.getCode().trim().isEmpty()) {
            throw new Exception("El código de la puerta de embarque es obligatorio");
        }
        if (boardingGate.getDescription() == null || boardingGate.getDescription().trim().isEmpty()) {
            throw new Exception("La descripción de la puerta de embarque es obligatoria");
        }
        if (boardingGate.getTerminal() == null) {
            throw new Exception("La terminal de la puerta de embarque es obligatoria");
        }

        // Validar que la terminal exista
        if (terminalService.findById(boardingGate.getTerminal().getId()).isEmpty()) {
            throw new Exception("La terminal especificada no existe");
        }

        // Validar longitudes
        if (boardingGate.getName().length() > 100) {
            throw new Exception("El nombre de la puerta de embarque no puede tener más de 100 caracteres");
        }
        if (boardingGate.getCode().length() > 10) {
            throw new Exception("El código de la puerta de embarque no puede tener más de 10 caracteres");
        }
        if (boardingGate.getDescription().length() > 255) {
            throw new Exception("La descripción de la puerta de embarque no puede tener más de 255 caracteres");
        }

        // Validar formato del código (solo letras mayúsculas y números)
        if (!boardingGate.getCode().matches("^[A-Z0-9]+$")) {
            throw new Exception("El código de la puerta de embarque solo puede contener letras mayúsculas y números");
        }

        // Validar unicidad
        String excludeId = boardingGate.getId() != null ? boardingGate.getId() : "";
        if (isNameInUse(boardingGate.getName(), excludeId)) {
            throw new Exception("Ya existe una puerta de embarque con este nombre");
        }
        if (isCodeInUse(boardingGate.getCode(), excludeId)) {
            throw new Exception("Ya existe una puerta de embarque con este código");
        }
        if (isCodeInUseInTerminal(boardingGate.getCode(), boardingGate.getTerminal().getId(), excludeId)) {
            throw new Exception("Ya existe una puerta de embarque con este código en la terminal especificada");
        }
        if (isNameInUseInTerminal(boardingGate.getName(), boardingGate.getTerminal().getId(), excludeId)) {
            throw new Exception("Ya existe una puerta de embarque con este nombre en la terminal especificada");
        }
    }

    @Override
    public boolean isNameInUse(String name, String excludeBoardingGateId) throws Exception {
        try {
            excludeBoardingGateId = excludeBoardingGateId != null ? excludeBoardingGateId : "";
            return repository.existsByNameAndIdNot(name.trim(), excludeBoardingGateId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el nombre de la puerta de embarque está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUse(String code, String excludeBoardingGateId) throws Exception {
        try {
            excludeBoardingGateId = excludeBoardingGateId != null ? excludeBoardingGateId : "";
            return repository.existsByCodeAndIdNot(code.trim().toUpperCase(), excludeBoardingGateId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código de la puerta de embarque está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUseInTerminal(String code, String terminalId, String excludeBoardingGateId) throws Exception {
        try {
            excludeBoardingGateId = excludeBoardingGateId != null ? excludeBoardingGateId : "";
            return repository.existsByCodeAndTerminalIdAndIdNot(code.trim().toUpperCase(), terminalId, excludeBoardingGateId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código de la puerta de embarque está en uso en la terminal: " + e.getMessage());
        }
    }

    @Override
    public boolean isNameInUseInTerminal(String name, String terminalId, String excludeBoardingGateId) throws Exception {
        try {
            excludeBoardingGateId = excludeBoardingGateId != null ? excludeBoardingGateId : "";
            return repository.existsByNameAndTerminalIdAndIdNot(name.trim(), terminalId, excludeBoardingGateId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el nombre de la puerta de embarque está en uso en la terminal: " + e.getMessage());
        }
    }

    @Override
    public BoardingGate save(BoardingGate entity) throws Exception {
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

            // Validar datos
            validateBoardingGateData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar la puerta de embarque: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, BoardingGate entity) throws Exception {
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

            entity.setId(id);
            validateBoardingGateData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la puerta de embarque: " + e.getMessage());
        }
    }
}