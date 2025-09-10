package com.SENA.FlightManagementSystem.Security.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Person;
import com.SENA.FlightManagementSystem.Security.Entity.Role;
import com.SENA.FlightManagementSystem.Security.Entity.User;
import com.SENA.FlightManagementSystem.Security.IRepository.IUserRepository;
import com.SENA.FlightManagementSystem.Security.IService.IPersonService;
import com.SENA.FlightManagementSystem.Security.IService.IRoleService;
import com.SENA.FlightManagementSystem.Security.IService.IUserService;

@Service
public class UserService extends ABaseService<User> implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IRoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected IBaseRepository<User, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        try {
            if (username == null || username.trim().isEmpty()) {
                throw new Exception("El nombre de usuario no puede estar vacío");
            }
            return repository.findByUsername(username.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar usuario por nombre de usuario: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByPersonEmail(String email) throws Exception {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("El email no puede estar vacío");
            }
            return repository.findByPersonEmail(email.trim().toLowerCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar usuario por email: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByPersonDocumentNumber(String documentNumber) throws Exception {
        try {
            if (documentNumber == null || documentNumber.trim().isEmpty()) {
                throw new Exception("El número de documento no puede estar vacío");
            }
            return repository.findByPersonDocumentNumber(documentNumber.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar usuario por número de documento: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> authenticate(String username, String password) throws Exception {
        try {
            Optional<User> userOpt = findByUsername(username);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();

            // Verificar si la cuenta está válida
            if (!isAccountValid(user)) {
                throw new Exception("Cuenta de usuario no válida o bloqueada");
            }

            // Verificar contraseña
            if (!passwordEncoder.matches(password, user.getPassword())) {
                incrementFailedAttempts(username);
                throw new Exception("Contraseña incorrecta");
            }

            // Reset failed attempts y actualizar último login
            resetFailedAttempts(username);
            updateLastLogin(user.getId());

            return userOpt;
        } catch (Exception e) {
            throw new Exception("Error en autenticación: " + e.getMessage());
        }
    }

    @Override
    public User createUser(String username, String password, String personId, List<String> roleIds) throws Exception {
        try {
            // Validar datos
            if (username == null || username.trim().isEmpty()) {
                throw new Exception("El nombre de usuario es obligatorio");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new Exception("La contraseña es obligatoria");
            }
            if (personId == null || personId.trim().isEmpty()) {
                throw new Exception("La persona es obligatoria");
            }

            // Verificar que la persona existe
            Optional<Person> personOpt = personService.findById(personId);
            if (personOpt.isEmpty()) {
                throw new Exception("Persona no encontrada");
            }

            // Verificar unicidad del username
            if (isUsernameInUse(username, null)) {
                throw new Exception("Ya existe un usuario con este nombre de usuario");
            }

            // Verificar que la persona no esté asociada a otro usuario
            if (isPersonInUse(personId, null)) {
                throw new Exception("Esta persona ya tiene un usuario asociado");
            }

            // Validar contraseña
            if (!isPasswordValid(password)) {
                throw new Exception("La contraseña no cumple con los requisitos de seguridad");
            }

            // Crear usuario
            User user = new User();
            user.setUsername(username.trim());
            user.setPassword(passwordEncoder.encode(password));
            user.setPerson(personOpt.get());
            user.setIsActive(true);
            user.setIsLocked(false);
            user.setFailedAttempts(0);

            // Asignar roles si se proporcionaron
            if (roleIds != null && !roleIds.isEmpty()) {
                user.setRoles(new HashSet<>());
                for (String roleId : roleIds) {
                    Optional<Role> roleOpt = roleService.findById(roleId);
                    if (roleOpt.isPresent()) {
                        user.addRole(roleOpt.get());
                    }
                }
            }

            return save(user);
        } catch (Exception e) {
            throw new Exception("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();

            // Verificar contraseña actual
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new Exception("La contraseña actual es incorrecta");
            }

            // Validar nueva contraseña
            if (!isPasswordValid(newPassword)) {
                throw new Exception("La nueva contraseña no cumple con los requisitos de seguridad");
            }

            // Actualizar contraseña
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPasswordExpiresAt(LocalDateTime.now().plusMonths(3)); // Expirar en 3 meses

            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al cambiar contraseña: " + e.getMessage());
        }
    }

    @Override
    public void resetPassword(String userId, String newPassword) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();

            // Validar nueva contraseña
            if (!isPasswordValid(newPassword)) {
                throw new Exception("La nueva contraseña no cumple con los requisitos de seguridad");
            }

            // Resetear contraseña
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPasswordExpiresAt(LocalDateTime.now().plusDays(30)); // Forzar cambio en 30 días
            user.setFailedAttempts(0);
            user.setIsLocked(false);

            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al resetear contraseña: " + e.getMessage());
        }
    }

    @Override
    public void lockUser(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setIsLocked(true);
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al bloquear usuario: " + e.getMessage());
        }
    }

    @Override
    public void unlockUser(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setIsLocked(false);
            user.setFailedAttempts(0);
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al desbloquear usuario: " + e.getMessage());
        }
    }

    @Override
    public void activateUser(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setIsActive(true);
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al activar usuario: " + e.getMessage());
        }
    }

    @Override
    public void deactivateUser(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setIsActive(false);
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al desactivar usuario: " + e.getMessage());
        }
    }

    @Override
    public void assignRoles(String userId, List<String> roleIds) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }

            for (String roleId : roleIds) {
                Optional<Role> roleOpt = roleService.findById(roleId);
                if (roleOpt.isPresent()) {
                    user.addRole(roleOpt.get());
                }
            }

            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al asignar roles: " + e.getMessage());
        }
    }

    @Override
    public void removeRoles(String userId, List<String> roleIds) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            if (user.getRoles() != null) {
                for (String roleId : roleIds) {
                    user.getRoles().removeIf(role -> role.getId().equals(roleId));
                }
                repository.save(user);
            }
        } catch (Exception e) {
            throw new Exception("Error al remover roles: " + e.getMessage());
        }
    }

    @Override
    public void addRole(String userId, String roleId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            Optional<Role> roleOpt = roleService.findById(roleId);

            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }
            if (roleOpt.isEmpty()) {
                throw new Exception("Rol no encontrado");
            }

            User user = userOpt.get();
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }

            user.addRole(roleOpt.get());
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al agregar rol: " + e.getMessage());
        }
    }

    @Override
    public void removeRole(String userId, String roleId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            if (user.getRoles() != null) {
                user.getRoles().removeIf(role -> role.getId().equals(roleId));
                repository.save(user);
            }
        } catch (Exception e) {
            throw new Exception("Error al remover rol: " + e.getMessage());
        }
    }

    @Override
    public List<Role> getUserRoles(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            return user.getRoles() != null ? new ArrayList<>(user.getRoles()) : new ArrayList<>();
        } catch (Exception e) {
            throw new Exception("Error al obtener roles del usuario: " + e.getMessage());
        }
    }

    @Override
    public boolean hasRole(String userId, String roleName) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                return false;
            }

            User user = userOpt.get();
            return user.hasRole(roleName);
        } catch (Exception e) {
            throw new Exception("Error al verificar rol del usuario: " + e.getMessage());
        }
    }

    @Override
    public boolean hasPermission(String userId, String permissionName) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                return false;
            }

            User user = userOpt.get();
            return user.hasPermission(permissionName);
        } catch (Exception e) {
            throw new Exception("Error al verificar permiso del usuario: " + e.getMessage());
        }
    }

    @Override
    public void updateLastLogin(String userId) throws Exception {
        try {
            Optional<User> userOpt = findById(userId);
            if (userOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setLastLogin(LocalDateTime.now());
            repository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al actualizar último login: " + e.getMessage());
        }
    }

    @Override
    public void incrementFailedAttempts(String username) throws Exception {
        try {
            Optional<User> userOpt = findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.incrementFailedAttempts();
                repository.save(user);
            }
        } catch (Exception e) {
            throw new Exception("Error al incrementar intentos fallidos: " + e.getMessage());
        }
    }

    @Override
    public void resetFailedAttempts(String username) throws Exception {
        try {
            Optional<User> userOpt = findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.resetFailedAttempts();
                repository.save(user);
            }
        } catch (Exception e) {
            throw new Exception("Error al resetear intentos fallidos: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAllActiveUsers() throws Exception {
        try {
            return repository.findAllActiveUsers();
        } catch (Exception e) {
            throw new Exception("Error al obtener usuarios activos: " + e.getMessage());
        }
    }

    @Override
    public List<User> findByRoleName(String roleName) throws Exception {
        try {
            return repository.findByRoleName(roleName);
        } catch (Exception e) {
            throw new Exception("Error al buscar usuarios por rol: " + e.getMessage());
        }
    }

    @Override
    public List<User> findLockedUsers() throws Exception {
        try {
            return repository.findLockedUsers();
        } catch (Exception e) {
            throw new Exception("Error al obtener usuarios bloqueados: " + e.getMessage());
        }
    }

    @Override
    public void validateUserData(User user) throws Exception {
        if (user == null) {
            throw new Exception("Los datos del usuario no pueden estar vacíos");
        }

        // Validar campos obligatorios
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new Exception("El nombre de usuario es obligatorio");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new Exception("La contraseña es obligatoria");
        }
        if (user.getPerson() == null) {
            throw new Exception("La persona asociada es obligatoria");
        }

        // Validar longitudes
        if (user.getUsername().length() > 50) {
            throw new Exception("El nombre de usuario no puede tener más de 50 caracteres");
        }

        // Validar formato del username (solo letras, números, puntos y guiones bajos)
        if (!user.getUsername().matches("^[a-zA-Z0-9._-]+$")) {
            throw new Exception("El nombre de usuario solo puede contener letras, números, puntos, guiones y guiones bajos");
        }

        // Validar unicidad
        String excludeId = user.getId() != null ? user.getId() : "";
        if (isUsernameInUse(user.getUsername(), excludeId)) {
            throw new Exception("Ya existe un usuario con este nombre de usuario");
        }
        if (isPersonInUse(user.getPerson().getId(), excludeId)) {
            throw new Exception("Esta persona ya tiene un usuario asociado");
        }
    }

    @Override
    public boolean isUsernameInUse(String username, String excludeUserId) throws Exception {
        try {
            excludeUserId = excludeUserId != null ? excludeUserId : "";
            return repository.existsByUsernameAndIdNot(username.trim(), excludeUserId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el username está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isPersonInUse(String personId, String excludeUserId) throws Exception {
        try {
            excludeUserId = excludeUserId != null ? excludeUserId : "";
            return repository.existsByPersonIdAndIdNot(personId, excludeUserId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si la persona está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isPasswordValid(String password) throws Exception {
        if (password == null || password.length() < 8) {
            return false;
        }

        // Validar que tenga al menos una mayúscula, una minúscula, un número y un carácter especial
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    @Override
    public boolean isAccountValid(User user) throws Exception {
        return user.isEnabled() && user.isAccountNonExpired() && 
               user.isAccountNonLocked() && user.isCredentialsNonExpired();
    }

    @Override
    public User save(User entity) throws Exception {
        try {
            // Normalizar datos antes de guardar
            if (entity.getUsername() != null) {
                entity.setUsername(entity.getUsername().trim().toLowerCase());
            }

            // Validar datos
            validateUserData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, User entity) throws Exception {
        try {
            // Normalizar datos antes de actualizar
            if (entity.getUsername() != null) {
                entity.setUsername(entity.getUsername().trim().toLowerCase());
            }

            entity.setId(id);
            validateUserData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el usuario: " + e.getMessage());
        }
    }
}