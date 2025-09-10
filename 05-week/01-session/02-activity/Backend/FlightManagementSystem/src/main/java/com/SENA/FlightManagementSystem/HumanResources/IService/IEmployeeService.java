package com.SENA.FlightManagementSystem.HumanResources.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.SENA.FlightManagementSystem.Parameterization.IService.IBaseService;
import com.SENA.FlightManagementSystem.HumanResources.Entity.Employee;

/**
 * Service interface for Employee entity.
 */
public interface IEmployeeService extends IBaseService<Employee> {
    
    /**
     * Find employee by person document number.
     */
    Optional<Employee> findByPersonDocumentNumber(String documentNumber) throws Exception;
    
    /**
     * Find employee by person email.
     */
    Optional<Employee> findByPersonEmail(String email) throws Exception;
    
    /**
     * Find employee by person ID.
     */
    Optional<Employee> findByPersonId(String personId) throws Exception;
    
    /**
     * Find employees by crew role.
     */
    List<Employee> findByCrewRoleId(String crewRoleId) throws Exception;
    
    /**
     * Find employees by crew role name.
     */
    List<Employee> findByCrewRoleName(String crewRoleName) throws Exception;
    
    /**
     * Find employees hired between dates.
     */
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate) throws Exception;
    
    /**
     * Find employees hired after date.
     */
    List<Employee> findByHireDateAfter(LocalDate hireDate) throws Exception;
    
    /**
     * Find employees with salary range.
     */
    List<Employee> findBySalaryBetween(java.math.BigDecimal minSalary, java.math.BigDecimal maxSalary) throws Exception;
    
    /**
     * Get all active employees.
     */
    List<Employee> findAllActiveEmployees() throws Exception;
    
    /**
     * Count employees by crew role.
     */
    long countByCrewRoleId(String crewRoleId) throws Exception;
    
    /**
     * Validate employee data before save/update.
     */
    void validateEmployeeData(Employee employee) throws Exception;
    
    /**
     * Check if person is already an employee.
     */
    boolean isPersonAlreadyEmployee(String personId, String excludeEmployeeId) throws Exception;
    
    /**
     * Check if person document number is already an employee.
     */
    boolean isPersonDocumentAlreadyEmployee(String documentNumber, String excludeEmployeeId) throws Exception;
    
    /**
     * Check if employee code is already in use.
     */
    boolean isCodeInUse(String code, String excludeEmployeeId) throws Exception;
    
    /**
     * Calculate employee seniority in years.
     */
    int calculateSeniority(String employeeId) throws Exception;
    
    /**
     * Update employee salary.
     */
    void updateSalary(String employeeId, java.math.BigDecimal newSalary) throws Exception;
    
    /**
     * Transfer employee to new crew role.
     */
    void transferToCrewRole(String employeeId, String newCrewRoleId) throws Exception;
    
    /**
     * Validate salary amount.
     */
    boolean isValidSalary(java.math.BigDecimal salary) throws Exception;
    
    /**
     * Validate hire date.
     */
    boolean isValidHireDate(LocalDate hireDate) throws Exception;
}