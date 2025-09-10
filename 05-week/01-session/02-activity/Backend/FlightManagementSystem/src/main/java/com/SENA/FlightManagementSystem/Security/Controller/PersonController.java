package com.SENA.FlightManagementSystem.Security.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENA.FlightManagementSystem.Parameterization.Controller.ABaseController;
import com.SENA.FlightManagementSystem.Parameterization.DTO.ApiResponseDto;
import com.SENA.FlightManagementSystem.Security.Entity.Person;
import com.SENA.FlightManagementSystem.Security.IService.IPersonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/person")
public class PersonController extends ABaseController<Person, IPersonService> {

    public PersonController(IPersonService service) {
        super(service, "Person");
    }

    /**
     * Find person by document number.
     */
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<ApiResponseDto<Optional<Person>>> findByDocumentNumber(@PathVariable String documentNumber) {
        try {
            Optional<Person> person = service.findByDocumentNumber(documentNumber);
            return ResponseEntity.ok(new ApiResponseDto<>("Persona encontrada", person, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find person by email.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDto<Optional<Person>>> findByEmail(@PathVariable String email) {
        try {
            Optional<Person> person = service.findByEmail(email);
            return ResponseEntity.ok(new ApiResponseDto<>("Persona encontrada", person, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}