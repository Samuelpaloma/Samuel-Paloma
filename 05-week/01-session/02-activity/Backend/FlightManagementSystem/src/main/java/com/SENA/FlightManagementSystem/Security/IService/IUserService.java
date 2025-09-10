package com.SENA.FlightManagementSystem.Security.IService;

import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Role;
import com.SENA.FlightManagementSystem.Security.Entity.User;

/**
 * Service interface for User entity.
 */
public interface IUserService extends IBaseService<User> {
    
    /**
     * Find user by username for authentication.
     */
    Optional<User> findByUsername(String username) throws Exception;
    
    /**
     * Find user by person email.
     */
    Optional<User> findByPersonEmail(String email) throws Exception;
    
    /**
     * Find user by person document number.
     */
    Optional<User> findByPersonDocumentNumber(String documentNumber) throws Exception;
    
    /**
     * Authenticate user credentials.
     */
    Optional<User> authenticate(String username, String password) throws Exception;
    
    /**
     * Create new user with person data.
     */
    User createUser(String username, String password, String personId, List<String> roleIds) throws Exception;
    
    /**
     * Change user password.
     */
    void changePassword(String userId, String oldPassword, String newPassword) throws Exception;
    
    /**
     * Reset user password (admin function).
     */
    void resetPassword(String userId, String newPassword) throws Exception;
    
    /**
     * Lock user account.
     */
    void lockUser(String userId) throws Exception;
    
    /**
     * Unlock user account.
     */
    void unlockUser(String userId) throws Exception;
    
    /**
     * Activate user account.
     */
    void activateUser(String userId) throws Exception;
    
    /**
     * Deactivate user account.
     */
    void deactivateUser(String userId) throws Exception;
    
    /**
     * Assign roles to user.
     */
    void assignRoles(String userId, List<String> roleIds) throws Exception;
    
    /**
     * Remove roles from user.
     */
    void removeRoles(String userId, List<String> roleIds) throws Exception;
    
    /**
     * Add single role to user.
     */
    void addRole(String userId, String roleId) throws Exception;
    
    /**
     * Remove single role from user.
     */
    void removeRole(String userId, String roleId) throws Exception;
    
    /**
     * Get all roles for a user.
     */
    List<Role> getUserRoles(String userId) throws Exception;
    
    /**
     * Check if user has specific role.
     */
    boolean hasRole(String userId, String roleName) throws Exception;
    
    /**
     * Check if user has specific permission.
     */
    boolean hasPermission(String userId, String permissionName) throws Exception;
    
    /**
     * Update last login timestamp.
     */
    void updateLastLogin(String userId) throws Exception;
    
    /**
     * Increment failed login attempts.
     */
    void incrementFailedAttempts(String username) throws Exception;
    
    /**
     * Reset failed login attempts.
     */
    void resetFailedAttempts(String username) throws Exception;
    
    /**
     * Get all active users.
     */
    List<User> findAllActiveUsers() throws Exception;
    
    /**
     * Get users by role name.
     */
    List<User> findByRoleName(String roleName) throws Exception;
    
    /**
     * Get locked users.
     */
    List<User> findLockedUsers() throws Exception;
    
    /**
     * Validate user data before save/update.
     */
    void validateUserData(User user) throws Exception;
    
    /**
     * Check if username is already in use by another user.
     */
    boolean isUsernameInUse(String username, String excludeUserId) throws Exception;
    
    /**
     * Check if person is already associated with another user.
     */
    boolean isPersonInUse(String personId, String excludeUserId) throws Exception;
    
    /**
     * Validate password strength.
     */
    boolean isPasswordValid(String password) throws Exception;
    
    /**
     * Check if user account is valid for login.
     */
    boolean isAccountValid(User user) throws Exception;
}