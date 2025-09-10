package com.SENA.FlightManagementSystem.HumanResources.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.HumanResources.Entity.Employee;
import com.SENA.FlightManagementSystem.HumanResources.IRepository.IEmployeeRepository;
import com.SENA.FlightManagementSystem.HumanResources.IService.IEmployeeService;
import com.SENA.FlightManagementSystem.Security.IService.IPersonService;
import com.SENA.FlightManagementSystem.Parameterization.IService.ICrewRoleService;

@Service
public class EmployeeService extends ABaseService<Employee> implements IEmployeeService {

    @Autowired
    private IEmployeeRepository repository;

    @Autowired
    private IPersonService personService;

    @Autowired
    private ICrewRoleService crewRoleService;

    @Override
    protected IBaseRepository<Employee, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<Employee> findByPersonDocumentNumber(String documentNumber) throws Exception {
        try {
            if (documentNumber == null || documentNumber.trim().isEmpty()) {
                throw new Exception("El número de documento no puede estar vacío");
            }
            return repository.findByPersonDocumentNumber(documentNumber.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar empleado por número de documento: " + e.getMessage());
        }
    }

    @Override
    public Optional<Employee> findByPersonEmail(String email) throws Exception {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("El email no puede estar vacío");
            }
            return repository.findByPersonEmail(email.trim().toLowerCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar empleado por email: " + e.getMessage());
        }
    }

    @Override
    public Optional<Employee> findByPersonId(String personId) throws Exception {
        try {
            if (personId == null || personId.trim().isEmpty()) {
                throw new Exception("El ID de la persona no puede estar vacío");
            }
            return repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new Exception("Error al buscar empleado por ID de persona: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findByCrewRoleId(String crewRoleId) throws Exception {
        try {
            if (crewRoleId == null || crewRoleId.trim().isEmpty()) {
                throw new Exception("El ID del rol de tripulación no puede estar vacío");
            }
            return repository.findByCrewRoleId(crewRoleId);
        } catch (Exception e) {
            throw new Exception("Error al buscar empleados por rol de tripulación: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findByCrewRoleName(String crewRoleName) throws Exception {
        try {
            if (crewRoleName == null || crewRoleName.trim().isEmpty()) {
                throw new Exception("El nombre del rol de tripulación no puede estar vacío");
            }
            return repository.findByCrewRoleName(crewRoleName.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar empleados por nombre del rol: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate) throws Exception {
        try {
            if (startDate == null || endDate == null) {
                throw new Exception("Las fechas de inicio y fin no pueden estar vacías");
            }
            if (startDate.isAfter(endDate)) {
                throw new Exception("La fecha de inicio no puede ser posterior a la fecha de fin");
            }
            return repository.findByHireDateBetween(startDate, endDate);
        } catch (Exception e) {
            throw new Exception("Error al buscar empleados por rango de fechas: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findByHireDateAfter(LocalDate hireDate) throws Exception {
        try {
            if (hireDate == null) {
                throw new Exception("La fecha de contratación no puede estar vacía");
            }
            return repository.findByHireDateAfter(hireDate);
        } catch (Exception e) {
            throw new Exception("Error al buscar empleados contratados después de la fecha: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) throws Exception {
        try {
            if (minSalary == null || maxSalary == null) {
                throw new Exception("Los salarios mínimo y máximo no pueden estar vacíos");
            }
            if (minSalary.compareTo(maxSalary) > 0) {
                throw new Exception("El salario mínimo no puede ser mayor al salario máximo");
            }
            return repository.findBySalaryBetween(minSalary, maxSalary);
        } catch (Exception e) {
            throw new Exception("Error al buscar empleados por rango salarial: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAllActiveEmployees() throws Exception {
        try {
            return repository.findAllActiveEmployees();
        } catch (Exception e) {
            throw new Exception("Error al obtener empleados activos: " + e.getMessage());
        }
    }

    @Override
    public long countByCrewRoleId(String crewRoleId) throws Exception {
        try {
            if (crewRoleId == null || crewRoleId.trim().isEmpty()) {
                throw new Exception("El ID del rol de tripulación no puede estar vacío");
            }
            return repository.countByCrewRoleId(crewRoleId);
        } catch (Exception e) {
            throw new Exception("Error al contar empleados por rol: " + e.getMessage());
        }
    }

    @Override
    public void validateEmployeeData(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("Los datos del empleado no pueden estar vacíos");
        }

        // Validar campos obligatorios
        if (employee.getSalary() == null) {
            throw new Exception("El salario del empleado es obligatorio");
        }
        if (employee.getHireDate() == null) {
            throw new Exception("La fecha de contratación es obligatoria");
        }
        if (employee.getCrewRole() == null) {
            throw new Exception("El rol de tripulación es obligatorio");
        }
        if (employee.getPerson() == null) {
            throw new Exception("La persona asociada es obligatoria");
        }

        // Validar que la persona exista
        if (personService.findById(employee.getPerson().getId()).isEmpty()) {
            throw new Exception("La persona especificada no existe");
        }

        // Validar que el rol de tripulación exista
        if (crewRoleService.findById(employee.getCrewRole().getId()).isEmpty()) {
            throw new Exception("El rol de tripulación especificado no existe");
        }

        // Validar campos heredados opcionales
        if (employee.getDescription() != null && employee.getDescription().length() > 255) {
            throw new Exception("La descripción del empleado no puede tener más de 255 caracteres");
        }
        if (employee.getCode() != null && employee.getCode().length() > 10) {
            throw new Exception("El código del empleado no puede tener más de 10 caracteres");
        }

        // Validar salario
        if (!isValidSalary(employee.getSalary())) {
            throw new Exception("El salario debe ser mayor a cero y no puede exceder 999,999,999.99");
        }

        // Validar fecha de contratación
        if (!isValidHireDate(employee.getHireDate())) {
            throw new Exception("La fecha de contratación no puede ser futura ni anterior a 1900");
        }

        // Validar unicidad
        String excludeId = employee.getId() != null ? employee.getId() : "";
        if (isPersonAlreadyEmployee(employee.getPerson().getId(), excludeId)) {
            throw new Exception("Esta persona ya es empleado");
        }
        if (employee.getCode() != null && isCodeInUse(employee.getCode(), excludeId)) {
            throw new Exception("Ya existe un empleado con este código");
        }
    }

    @Override
    public boolean isPersonAlreadyEmployee(String personId, String excludeEmployeeId) throws Exception {
        try {
            excludeEmployeeId = excludeEmployeeId != null ? excludeEmployeeId : "";
            return repository.existsByPersonIdAndIdNot(personId, excludeEmployeeId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si la persona ya es empleado: " + e.getMessage());
        }
    }

    @Override
    public boolean isPersonDocumentAlreadyEmployee(String documentNumber, String excludeEmployeeId) throws Exception {
        try {
            excludeEmployeeId = excludeEmployeeId != null ? excludeEmployeeId : "";
            return repository.existsByPersonDocumentNumberAndIdNot(documentNumber.trim(), excludeEmployeeId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el documento ya tiene empleado: " + e.getMessage());
        }
    }

    @Override
    public boolean isCodeInUse(String code, String excludeEmployeeId) throws Exception {
        try {
            excludeEmployeeId = excludeEmployeeId != null ? excludeEmployeeId : "";
            return repository.existsByCodeAndIdNot(code.trim().toUpperCase(), excludeEmployeeId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el código está en uso: " + e.getMessage());
        }
    }

    @Override
    public int calculateSeniority(String employeeId) throws Exception {
        try {
            Optional<Employee> employeeOpt = findById(employeeId);
            if (employeeOpt.isEmpty()) {
                throw new Exception("Empleado no encontrado");
            }
            return employeeOpt.get().getYearsOfService();
        } catch (Exception e) {
            throw new Exception("Error al calcular antigüedad: " + e.getMessage());
        }
    }

    @Override
    public void updateSalary(String employeeId, BigDecimal newSalary) throws Exception {
        try {
            Optional<Employee> employeeOpt = findById(employeeId);
            if (employeeOpt.isEmpty()) {
                throw new Exception("Empleado no encontrado");
            }

            if (!isValidSalary(newSalary)) {
                throw new Exception("El nuevo salario no es válido");
            }

            Employee employee = employeeOpt.get();
            employee.setSalary(newSalary);
            repository.save(employee);
        } catch (Exception e) {
            throw new Exception("Error al actualizar salario: " + e.getMessage());
        }
    }

    @Override
    public void transferToCrewRole(String employeeId, String newCrewRoleId) throws Exception {
        try {
            Optional<Employee> employeeOpt = findById(employeeId);
            if (employeeOpt.isEmpty()) {
                throw new Exception("Empleado no encontrado");
            }

            if (crewRoleService.findById(newCrewRoleId).isEmpty()) {
                throw new Exception("El nuevo rol de tripulación no existe");
            }

            Employee employee = employeeOpt.get();
            employee.getCrewRole().setId(newCrewRoleId);
            repository.save(employee);
        } catch (Exception e) {
            throw new Exception("Error al transferir empleado: " + e.getMessage());
        }
    }

    @Override
    public boolean isValidSalary(BigDecimal salary) throws Exception {
        if (salary == null) return false;
        return salary.compareTo(BigDecimal.ZERO) > 0 && 
               salary.compareTo(new BigDecimal("999999999.99")) <= 0;
    }

    @Override
    public boolean isValidHireDate(LocalDate hireDate) throws Exception {
        if (hireDate == null) return false;
        LocalDate now = LocalDate.now();
        LocalDate minDate = LocalDate.of(1900, 1, 1);
        return !hireDate.isAfter(now) && !hireDate.isBefore(minDate);
    }

    @Override
    public Employee save(Employee entity) throws Exception {
        try {
            // Normalizar datos antes de guardar
            if (entity.getCode() != null) {
                entity.setCode(entity.getCode().trim().toUpperCase());
            }
            if (entity.getDescription() != null) {
                entity.setDescription(entity.getDescription().trim());
            }

            // Validar datos
            validateEmployeeData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar el empleado: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, Employee entity) throws Exception {
        try {
            // Normalizar datos antes de actualizar
            if (entity.getCode() != null) {
                entity.setCode(entity.getCode().trim().toUpperCase());
            }
            if (entity.getDescription() != null) {
                entity.setDescription(entity.getDescription().trim());
            }

            entity.setId(id);
            validateEmployeeData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el empleado: " + e.getMessage());
        }
    }
}