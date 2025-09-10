package com.SENA.FlightManagementSystem.Security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Security.Entity.User;
import java.time.LocalDateTime;

/**
 * Repository interface for User entity.
 */
@Repository
public interface IUserRepository extends IBaseRepository<User, String> {
    
    /**
     * Find user by username for authentication.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions WHERE u.username = :username AND u.status = true AND u.deletedAt IS NULL")
    Optional<User> findByUsername(@Param("username") String username);
    
    /**
     * Find user by person email.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions WHERE u.person.email = :email AND u.status = true AND u.deletedAt IS NULL")
    Optional<User> findByPersonEmail(@Param("email") String email);
    
    /**
     * Find user by person document number.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions WHERE u.person.documentNumber = :documentNumber AND u.status = true AND u.deletedAt IS NULL")
    Optional<User> findByPersonDocumentNumber(@Param("documentNumber") String documentNumber);
    
    /**
     * Find all active users (not locked, not expired).
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.isLocked = false AND u.status = true AND u.deletedAt IS NULL")
    List<User> findAllActiveUsers();
    
    /**
     * Find users by role name.
     */
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName AND u.status = true AND u.deletedAt IS NULL")
    List<User> findByRoleName(@Param("roleName") String roleName);
    
    /**
     * Check if username exists (excluding specific user).
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.id != :userId AND u.status = true AND u.deletedAt IS NULL")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("userId") String userId);
    
    /**
     * Check if person is already associated with another user.
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.person.id = :personId AND u.id != :userId AND u.status = true AND u.deletedAt IS NULL")
    boolean existsByPersonIdAndIdNot(@Param("personId") String personId, @Param("userId") String userId);
    
    /**
     * Find locked users.
     */
    @Query("SELECT u FROM User u WHERE u.isLocked = true AND u.status = true AND u.deletedAt IS NULL")
    List<User> findLockedUsers();
    
    /**
     * Count failed login attempts in last 24 hours.
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.username = :username AND u.failedAttempts > 0 AND u.lastLogin > :since")
    long countRecentFailedAttempts(@Param("username") String username, @Param("since") LocalDateTime since);
}