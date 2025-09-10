package com.SENA.FlightManagementSystem.Security.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Permission;
import com.SENA.FlightManagementSystem.Security.Entity.Role;
import com.SENA.FlightManagementSystem.Security.IRepository.IRoleRepository;
import com.SENA.FlightManagementSystem.Security.IService.IPermissionService;
import com.SENA.FlightManagementSystem.Security.IService.IRoleService;

@Service
public class RoleService extends ABaseService<Role> implements IRoleService {

    @Autowired
    private IRoleRepository repository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    protected IBaseRepository<Role, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<Role> findByName(String name) throws Exception {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("El nombre del rol no puede estar vacío");
            }
            return repository.findByName(name.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar rol por nombre: " + e.getMessage());
        }
    }

    @Override
    public Optional<Role> findByCode(String code) throws Exception {
        try {
            if (code == null || code.trim().isEmpty()) {
                throw new Exception("El código del rol no puede estar vacío");
            }
            return repository.findByCode(code.trim().toUpperCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar rol por código: " + e.getMessage());
        }
    }

    @Override
    public void assignPermissions(String roleId, List<String> permissionIds) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }

            Role role = roleOpt.get();
            if (role.getPermissions() == null) {
                role.setPermissions(new HashSet<>());
            }

            for (String permissionId : permissionIds) {
                Optional<Permission> permissionOpt = permissionService.findById(permissionId);
                if (permissionOpt.isPresent()) {
                    role.addPermission(permissionOpt.get());
                }
            }

            repository.save(role);
        } catch (Exception e) {
            throw new Exception("Error al asignar permisos al rol: " + e.getMessage());
        }
    }

    @Override
    public void removePermissions(String roleId, List<String> permissionIds) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }

            Role role = roleOpt.get();
            if (role.getPermissions() != null) {
                for (String permissionId : permissionIds) {
                    role.getPermissions().removeIf(permission -> permission.getId().equals(permissionId));
                }
                repository.save(role);
            }
        } catch (Exception e) {
            throw new Exception("Error al remover permisos del rol: " + e.getMessage());
        }
    }

    @Override
    public void addPermission(String roleId, String permissionId) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            Optional<Permission> permissionOpt = permissionService.findById(permissionId);

            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }
            if (permissionOpt.isEmpty()) {
                throw new Exception("Permiso no encontrado");
            }

            Role role = roleOpt.get();
            if (role.getPermissions() == null) {
                role.setPermissions(new HashSet<>());
            }

            role.addPermission(permissionOpt.get());
            repository.save(role);
        } catch (Exception e) {
            throw new Exception("Error al agregar permiso al rol: " + e.getMessage());
        }
    }

    @Override
    public void removePermission(String roleId, String permissionId) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }

            Role role = roleOpt.get();
            if (role.getPermissions() != null) {
                role.getPermissions().removeIf(permission -> permission.getId().equals(permissionId));
                repository.save(role);
            }
        } catch (Exception e) {
            throw new Exception("Error al remover permiso del rol: " + e.getMessage());
        }
    }

    @Override
    public List<Permission> getRolePermissions(String roleId) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }

            Role role = roleOpt.get();
            return role.getPermissions() != null ? new ArrayList<>(role.getPermissions()) : new ArrayList<>();
        } catch (Exception e) {
            throw new Exception("Error al obtener permisos del rol: " + e.getMessage());
        }
    }

    @Override
    public boolean hasPermission(String roleId, String permissionName) throws Exception {
        try {
            Optional<Role> roleOpt = findById(roleId);
            if (roleOpt.isEmpty()) {
                return false;
            }

            Role role = roleOpt.get();
            return role.hasPermission(permissionName);
        } catch (Exception e) {
            throw new Exception("Error al verificar permiso del rol: " + e.getMessage());
        }
    }

    @Override
    public void validateRoleData(Role role) throws Exception {
        if (role == null) {
            throw new Exception("Los datos del rol no pueden estar vacíos");
        }

        // Validar campos obligatorios heredados de ABaseEntity
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new Exception("El nombre del rol es obligatorio");
        }
        if (role.getCode() == null || role.getCode().trim().isEmpty()) {
            throw new Exception("El código del rol es obligatorio");
        }
        if (role.getDescription() == null || role.getDescription().trim().isEmpty()) {
            throw new Exception("La descripción del rol es obligatoria");
        }

        // Validar longitudes
        if (role.getName().length() > 100) {
            throw new Exception("El nombre del rol no puede tener más de 100 caracteres");
        }
        if (role.getCode().length() > 10) {
            throw new Exception("El código del rol no puede tener más de 10 caracteres");
        }
        if (role.getDescription().length() > 255) {
            throw new Exception("La descripción del rol no puede tener más de 255 caracteres");
        }

        // Validar formato del código (solo letras mayúsculas, números y guiones bajos)
        if (!role.getCode().matches("^[A-Z0-9_]+$")) {
            throw new Exception("El código del rol solo puede contener letras mayúsculas, números y guiones bajos");
        }

        // Validar unicidad
        String excludeId = role.getId() != null ? role.getId() : "";
        if (isNameInUse(role.getName(), excludeId)) {
            throw new Exception("Ya existe un rol con este nombre");
        }
        if (isCodeInUse(role.getCode(), excludeId)) {
            throw new Exception("Ya existe un rol con este código");
        }
    }

    @Override
    public boolean isNameInUse(String name, String excludeRoleId) throws Exception {
        try {
            excludeRoleId = excludeRoleId != null ? excludeRoleId : "";
            return repository.existsByNameAndIdNot(name.trim(), excludeRoleId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el nombre del rol está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUse(String code, String excludeRoleId) throws Exception {
        try {
            excludeRoleId = excludeRoleId != null ? excludeRoleId : "";
            return repository.existsByCodeAndIdNot(code.trim().toUpperCase(), excludeRoleId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código del rol está en uso: " + e.getMessage());
        }
    }

    @Override
    public Role save(Role entity) throws Exception {
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
            validateRoleData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar el rol: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, Role entity) throws Exception {
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
            validateRoleData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el rol: " + e.getMessage());
        }
    }
}