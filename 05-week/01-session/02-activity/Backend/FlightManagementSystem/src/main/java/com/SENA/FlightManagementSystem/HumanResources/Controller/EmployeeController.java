package com.SENA.FlightManagementSystem.HumanResources.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.Parameterization.Controller.ABaseController;
import com.SENA.FlightManagementSystem.Parameterization.DTO.ApiResponseDto;
import com.SENA.FlightManagementSystem.HumanResources.Entity.Employee;
import com.SENA.FlightManagementSystem.HumanResources.IService.IEmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/human-resources/employee")
public class EmployeeController extends ABaseController<Employee, IEmployeeService> {

    public EmployeeController(IEmployeeService service) {
        super(service, "Employee");
    }

    /**
     * Find employee by person document number.
     */
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<ApiResponseDto<Optional<Employee>>> findByPersonDocumentNumber(@PathVariable String documentNumber) {
        try {
            Optional<Employee> employee = service.findByPersonDocumentNumber(documentNumber);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleado encontrado", employee, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employee by person email.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDto<Optional<Employee>>> findByPersonEmail(@PathVariable String email) {
        try {
            Optional<Employee> employee = service.findByPersonEmail(email);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleado encontrado", employee, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employee by person ID.
     */
    @GetMapping("/person/{personId}")
    public ResponseEntity<ApiResponseDto<Optional<Employee>>> findByPersonId(@PathVariable String personId) {
        try {
            Optional<Employee> employee = service.findByPersonId(personId);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleado encontrado", employee, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employees by crew role.
     */
    @GetMapping("/crew-role/{crewRoleId}")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findByCrewRoleId(@PathVariable String crewRoleId) {
        try {
            List<Employee> employees = service.findByCrewRoleId(crewRoleId);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados encontrados", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employees by crew role name.
     */
    @GetMapping("/crew-role-name/{crewRoleName}")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findByCrewRoleName(@PathVariable String crewRoleName) {
        try {
            List<Employee> employees = service.findByCrewRoleName(crewRoleName);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados encontrados", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employees hired between dates.
     */
    @GetMapping("/hire-date-range")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findByHireDateBetween(
            @RequestParam LocalDate startDate, 
            @RequestParam LocalDate endDate) {
        try {
            List<Employee> employees = service.findByHireDateBetween(startDate, endDate);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados encontrados", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employees hired after date.
     */
    @GetMapping("/hire-date-after")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findByHireDateAfter(@RequestParam LocalDate hireDate) {
        try {
            List<Employee> employees = service.findByHireDateAfter(hireDate);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados encontrados", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find employees with salary range.
     */
    @GetMapping("/salary-range")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findBySalaryBetween(
            @RequestParam BigDecimal minSalary, 
            @RequestParam BigDecimal maxSalary) {
        try {
            List<Employee> employees = service.findBySalaryBetween(minSalary, maxSalary);
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados encontrados", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Get all active employees.
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponseDto<List<Employee>>> findAllActiveEmployees() {
        try {
            List<Employee> employees = service.findAllActiveEmployees();
            return ResponseEntity.ok(new ApiResponseDto<>("Empleados activos obtenidos", employees, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Count employees by crew role.
     */
    @GetMapping("/count/crew-role/{crewRoleId}")
    public ResponseEntity<ApiResponseDto<Long>> countByCrewRoleId(@PathVariable String crewRoleId) {
        try {
            long count = service.countByCrewRoleId(crewRoleId);
            return ResponseEntity.ok(new ApiResponseDto<>("Conteo completado", count, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Calculate employee seniority.
     */
    @GetMapping("/{employeeId}/seniority")
    public ResponseEntity<ApiResponseDto<Integer>> calculateSeniority(@PathVariable String employeeId) {
        try {
            int seniority = service.calculateSeniority(employeeId);
            return ResponseEntity.ok(new ApiResponseDto<>("Antigüedad calculada", seniority, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Update employee salary.
     */
    @PutMapping("/{employeeId}/salary")
    public ResponseEntity<ApiResponseDto<String>> updateSalary(@PathVariable String employeeId, 
                                                               @RequestBody SalaryUpdateRequest request) {
        try {
            service.updateSalary(employeeId, request.getNewSalary());
            return ResponseEntity.ok(new ApiResponseDto<>("Salario actualizado correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Transfer employee to new crew role.
     */
    @PutMapping("/{employeeId}/transfer")
    public ResponseEntity<ApiResponseDto<String>> transferToCrewRole(@PathVariable String employeeId, 
                                                                     @RequestBody CrewRoleTransferRequest request) {
        try {
            service.transferToCrewRole(employeeId, request.getNewCrewRoleId());
            return ResponseEntity.ok(new ApiResponseDto<>("Empleado transferido correctamente", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    // DTOs for request bodies
    public static class SalaryUpdateRequest {
        private BigDecimal newSalary;

        // Getters and setters
        public BigDecimal getNewSalary() { return newSalary; }
        public void setNewSalary(BigDecimal newSalary) { this.newSalary = newSalary; }
    }

    public static class CrewRoleTransferRequest {
        private String newCrewRoleId;

        // Getters and setters
        public String getNewCrewRoleId() { return newCrewRoleId; }
        public void setNewCrewRoleId(String newCrewRoleId) { this.newCrewRoleId = newCrewRoleId; }
    }
}