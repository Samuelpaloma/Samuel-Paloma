package com.SENA.FlightManagementSystem.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SENA.FlightManagementSystem.Parameterization.IRepository.IBaseRepository;
import com.SENA.FlightManagementSystem.Parameterization.Service.ABaseService;
import com.SENA.FlightManagementSystem.Security.Entity.Person;
import com.SENA.FlightManagementSystem.Security.IRepository.IPersonRepository;
import com.SENA.FlightManagementSystem.Security.IService.IPersonService;

@Service
public class PersonService extends ABaseService<Person> implements IPersonService {

    @Autowired
    private IPersonRepository repository;

    @Override
    protected IBaseRepository<Person, String> getRepository() {
        return repository;
    }

    @Override
    public Optional<Person> findByDocumentNumber(String documentNumber) throws Exception {
        try {
            if (documentNumber == null || documentNumber.trim().isEmpty()) {
                throw new Exception("El número de documento no puede estar vacío");
            }
            return repository.findByDocumentNumber(documentNumber.trim());
        } catch (Exception e) {
            throw new Exception("Error al buscar persona por número de documento: " + e.getMessage());
        }
    }

    @Override
    public Optional<Person> findByEmail(String email) throws Exception {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("El email no puede estar vacío");
            }
            return repository.findByEmail(email.trim().toLowerCase());
        } catch (Exception e) {
            throw new Exception("Error al buscar persona por email: " + e.getMessage());
        }
    }

    @Override
    public void validatePersonData(Person person) throws Exception {
        if (person == null) {
            throw new Exception("Los datos de la persona no pueden estar vacíos");
        }

        // Validar campos obligatorios
        if (person.getFirstName() == null || person.getFirstName().trim().isEmpty()) {
            throw new Exception("El primer nombre es obligatorio");
        }
        if (person.getFirstSurname() == null || person.getFirstSurname().trim().isEmpty()) {
            throw new Exception("El primer apellido es obligatorio");
        }
        if (person.getDocumentNumber() == null || person.getDocumentNumber().trim().isEmpty()) {
            throw new Exception("El número de documento es obligatorio");
        }
        if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
            throw new Exception("El email es obligatorio");
        }
        if (person.getDocumentType() == null) {
            throw new Exception("El tipo de documento es obligatorio");
        }

        // Validar formato del email
        if (!isValidEmail(person.getEmail())) {
            throw new Exception("El formato del email no es válido");
        }

        // Validar longitudes
        if (person.getFirstName().length() > 50) {
            throw new Exception("El primer nombre no puede tener más de 50 caracteres");
        }
        if (person.getFirstSurname().length() > 50) {
            throw new Exception("El primer apellido no puede tener más de 50 caracteres");
        }
        if (person.getDocumentNumber().length() > 20) {
            throw new Exception("El número de documento no puede tener más de 20 caracteres");
        }
        if (person.getEmail().length() > 100) {
            throw new Exception("El email no puede tener más de 100 caracteres");
        }

        // Validar unicidad
        String excludeId = person.getId() != null ? person.getId() : "";
        if (isEmailInUse(person.getEmail(), excludeId)) {
            throw new Exception("Ya existe una persona con este email");
        }
        if (isDocumentNumberInUse(person.getDocumentNumber(), excludeId)) {
            throw new Exception("Ya existe una persona con este número de documento");
        }
    }

    @Override
    public boolean isEmailInUse(String email, String excludePersonId) throws Exception {
        try {
            excludePersonId = excludePersonId != null ? excludePersonId : "";
            return repository.existsByEmailAndIdNot(email.trim().toLowerCase(), excludePersonId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el email está en uso: " + e.getMessage());
        }
    }

    @Override
    public boolean isDocumentNumberInUse(String documentNumber, String excludePersonId) throws Exception {
        try {
            excludePersonId = excludePersonId != null ? excludePersonId : "";
            return repository.existsByDocumentNumberAndIdNot(documentNumber.trim(), excludePersonId);
        } catch (Exception e) {
            throw new Exception("Error al verificar si el número de documento está en uso: " + e.getMessage());
        }
    }

    @Override
    public Person save(Person entity) throws Exception {
        try {
            // Normalizar datos antes de guardar
            if (entity.getEmail() != null) {
                entity.setEmail(entity.getEmail().trim().toLowerCase());
            }
            if (entity.getDocumentNumber() != null) {
                entity.setDocumentNumber(entity.getDocumentNumber().trim());
            }
            if (entity.getFirstName() != null) {
                entity.setFirstName(entity.getFirstName().trim());
            }
            if (entity.getFirstSurname() != null) {
                entity.setFirstSurname(entity.getFirstSurname().trim());
            }

            // Validar datos
            validatePersonData(entity);

            return super.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar la persona: " + e.getMessage());
        }
    }

    @Override
    public void update(String id, Person entity) throws Exception {
        try {
            // Normalizar datos antes de actualizar
            if (entity.getEmail() != null) {
                entity.setEmail(entity.getEmail().trim().toLowerCase());
            }
            if (entity.getDocumentNumber() != null) {
                entity.setDocumentNumber(entity.getDocumentNumber().trim());
            }
            if (entity.getFirstName() != null) {
                entity.setFirstName(entity.getFirstName().trim());
            }
            if (entity.getFirstSurname() != null) {
                entity.setFirstSurname(entity.getFirstSurname().trim());
            }

            entity.setId(id);
            validatePersonData(entity);

            super.update(id, entity);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la persona: " + e.getMessage());
        }
    }

    /**
     * Validate email format using simple regex.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }
}