package com.tecsup.vacante.controller;

import com.tecsup.vacante.dto.VacanteRequest;
import com.tecsup.vacante.model.Vacante;
import com.tecsup.vacante.services.VacanteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// Capa HTTP: solo recibe la petición y delega en el servicio.
@RestController
@RequestMapping("/api/vacantes")
public class VacanteController {

    private final VacanteService service;

    public VacanteController(VacanteService service) {
        this.service = service;
    }

    // GET /api/vacantes o GET /api/vacantes?idEmpresa=1
    @GetMapping
    public List<Vacante> listar(@RequestParam(required = false) Long idEmpresa) {
        return service.listar(idEmpresa);
    }

    @GetMapping("/{id}")
    public Vacante buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Vacante registrar(@Valid @RequestBody VacanteRequest datos) {
        return service.registrar(datos);
    }

    @PutMapping("/{id}")
    public Vacante actualizar(@PathVariable Long id, @Valid @RequestBody VacanteRequest datos) {
        return service.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
