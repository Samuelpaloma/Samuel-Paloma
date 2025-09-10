package com.SENA.FlightManagementSystem.HumanResources.IRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.HumanResources.Entity.Employee;

/**
 * Repository interface for Employee entity.
 */
@Repository
public interface IEmployeeRepository extends IBaseRepository<Employee, String> {
    
    /**
     * Find employee by person document number.
     */
    @Query("SELECT e FROM Employee e WHERE e.person.documentNumber = :documentNumber AND e.status = true AND e.deletedAt IS NULL")
    Optional<Employee> findByPersonDocumentNumber(@Param("documentNumber") String documentNumber);
    
    /**
     * Find employee by person email.
     */
    @Query("SELECT e FROM Employee e WHERE e.person.email = :email AND e.status = true AND e.deletedAt IS NULL")
    Optional<Employee> findByPersonEmail(@Param("email") String email);
    
    /**
     * Find employee by person ID.
     */
    @Query("SELECT e FROM Employee e WHERE e.person.id = :personId AND e.status = true AND e.deletedAt IS NULL")
    Optional<Employee> findByPersonId(@Param("personId") String personId);
    
    /**
     * Find employees by crew role.
     */
    @Query("SELECT e FROM Employee e WHERE e.crewRole.id = :crewRoleId AND e.status = true AND e.deletedAt IS NULL")
    List<Employee> findByCrewRoleId(@Param("crewRoleId") String crewRoleId);
    
    /**
     * Find employees by crew role name.
     */
    @Query("SELECT e FROM Employee e WHERE e.crewRole.name = :crewRoleName AND e.status = true AND e.deletedAt IS NULL")
    List<Employee> findByCrewRoleName(@Param("crewRoleName") String crewRoleName);
    
    /**
     * Find employees hired between dates.
     */
    @Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN :startDate AND :endDate AND e.status = true AND e.deletedAt IS NULL")
    List<Employee> findByHireDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    /**
     * Find employees hired after date.
     */
    @Query("SELECT e FROM Employee e WHERE e.hireDate >= :hireDate AND e.status = true AND e.deletedAt IS NULL")
    List<Employee> findByHireDateAfter(@Param("hireDate") LocalDate hireDate);
    
    /**
     * Find employees with salary range.
     */
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary AND e.status = true AND e.deletedAt IS NULL")
    List<Employee> findBySalaryBetween(@Param("minSalary") java.math.BigDecimal minSalary, @Param("maxSalary") java.math.BigDecimal maxSalary);
    
    /**
     * Check if person is already an employee (excluding specific employee).
     */
    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.person.id = :personId AND e.id != :employeeId AND e.status = true AND e.deletedAt IS NULL")
    boolean existsByPersonIdAndIdNot(@Param("personId") String personId, @Param("employeeId") String employeeId);
    
    /**
     * Check if person document number is already an employee (excluding specific employee).
     */
    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.person.documentNumber = :documentNumber AND e.id != :employeeId AND e.status = true AND e.deletedAt IS NULL")
    boolean existsByPersonDocumentNumberAndIdNot(@Param("documentNumber") String documentNumber, @Param("employeeId") String employeeId);
    
    /**
     * Check if employee code exists (excluding specific employee).
     */
    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.code = :code AND e.id != :employeeId AND e.status = true AND e.deletedAt IS NULL")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("employeeId") String employeeId);
    
    /**
     * Count employees by crew role.
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.crewRole.id = :crewRoleId AND e.status = true AND e.deletedAt IS NULL")
    long countByCrewRoleId(@Param("crewRoleId") String crewRoleId);
    
    /**
     * Find active employees (excluding specific statuses).
     */
    @Query("SELECT e FROM Employee e WHERE e.status = true AND e.deletedAt IS NULL ORDER BY e.person.firstName, e.person.firstSurname")
    List<Employee> findAllActiveEmployees();
}