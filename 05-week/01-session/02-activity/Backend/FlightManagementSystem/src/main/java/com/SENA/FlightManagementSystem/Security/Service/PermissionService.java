package com.SENA.FlightManagementSystem.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;
import com.SENA.FlightManagementSystem.Security.IRepository.IPermissionRepository;
import com.SENA.FlightManagementSystem.Security.IService.IPermissionService;

@Service
public class PermissionService extends ABaseService<Permission> implements IPermissionService {

    @Autowired
    private IPermissionRepository repository;

    @Override
    protected IBaseRepository<Permission, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<Permission> findByName(String name) throws Exception {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("El nombre del permiso no puede estar vacío");
            }
            return repository.findByName(name.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar permiso por nombre: " + e.getMessage());
        }
    }

    @Override
    public Optional<Permission> findByCode(String code) throws Exception {
        try {
            if (code == null || code.trim().isEmpty()) {
                throw new Exception("El código del permiso no puede estar vacío");
            }
            return repository.findByCode(code.trim().toUpperCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar permiso por código: " + e.getMessage());
        }
    }

    @Override
    public void validatePermissionData(Permission permission) throws Exception {
        if (permission == null) {
            throw new Exception("Los datos del permiso no pueden estar vacíos");
        }

        // Validar campos obligatorios heredados de ABaseEntity
        if (permission.getName() == null || permission.getName().trim().isEmpty()) {
            throw new Exception("El nombre del permiso es obligatorio");
        }
        if (permission.getCode() == null || permission.getCode().trim().isEmpty()) {
            throw new Exception("El código del permiso es obligatorio");
        }
        if (permission.getDescription() == null || permission.getDescription().trim().isEmpty()) {
            throw new Exception("La descripción del permiso es obligatoria");
        }

        // Validar longitudes
        if (permission.getName().length() > 100) {
            throw new Exception("El nombre del permiso no puede tener más de 100 caracteres");
        }
        if (permission.getCode().length() > 10) {
            throw new Exception("El código del permiso no puede tener más de 10 caracteres");
        }
        if (permission.getDescription().length() > 255) {
            throw new Exception("La descripción del permiso no puede tener más de 255 caracteres");
        }

        // Validar formato del código (solo letras mayúsculas, números y guiones bajos)
        if (!permission.getCode().matches("^[A-Z0-9_]+$")) {
            throw new Exception("El código del permiso solo puede contener letras mayúsculas, números y guiones bajos");
        }

        // Validar unicidad
        String excludeId = permission.getId() != null ? permission.getId() : "";
        if (isNameInUse(permission.getName(), excludeId)) {
            throw new Exception("Ya existe un permiso con este nombre");
        }
        if (isCodeInUse(permission.getCode(), excludeId)) {
            throw new Exception("Ya existe un permiso con este código");
        }
    }

    @Override
    public boolean isNameInUse(String name, String excludePermissionId) throws Exception {
        try {
            excludePermissionId = excludePermissionId != null ? excludePermissionId : "";
            return repository.existsByNameAndIdNot(name.trim(), excludePermissionId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el nombre del permiso está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUse(String code, String excludePermissionId) throws Exception {
        try {
            excludePermissionId = excludePermissionId != null ? excludePermissionId : "";
            return repository.existsByCodeAndIdNot(code.trim().toUpperCase(), excludePermissionId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código del permiso está en uso: " + e.getMessage());
        }
    }

    @Override
    public Permission save(Permission entity) throws Exception {
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
            validatePermissionData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar el permiso: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, Permission entity) throws Exception {
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
            validatePermissionData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el permiso: " + e.getMessage());
        }
    }
}