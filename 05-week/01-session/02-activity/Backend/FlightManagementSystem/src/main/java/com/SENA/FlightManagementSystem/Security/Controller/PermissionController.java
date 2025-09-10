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
import com.SENA.FlightManagementSystem.Security.Entity.Permission;
import com.SENA.FlightManagementSystem.Security.IService.IPermissionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/permission")
public class PermissionController extends ABaseController<Permission, IPermissionService> {

    public PermissionController(IPermissionService service) {
        super(service, "Permission");
    }

    /**
     * Find permission by name.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<Optional<Permission>>> findByName(@PathVariable String name) {
        try {
            Optional<Permission> permission = service.findByName(name);
            return ResponseEntity.ok(new ApiResponseDto<>("Permiso encontrado", permission, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Find permission by code.
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponseDto<Optional<Permission>>> findByCode(@PathVariable String code) {
        try {
            Optional<Permission> permission = service.findByCode(code);
            return ResponseEntity.ok(new ApiResponseDto<>("Permiso encontrado", permission, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}