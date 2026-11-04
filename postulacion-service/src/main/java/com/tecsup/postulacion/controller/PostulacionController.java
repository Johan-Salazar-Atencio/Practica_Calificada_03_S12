package com.tecsup.postulacion.controller;

import com.tecsup.postulacion.dto.PostulacionRequest;
import com.tecsup.postulacion.model.Postulacion;
import com.tecsup.postulacion.services.PostulacionService;
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
@RequestMapping("/api/postulaciones")
public class PostulacionController {

    private final PostulacionService service;

    public PostulacionController(PostulacionService service) {
        this.service = service;
    }

    // GET /api/postulaciones o GET /api/postulaciones?idVacante=1
    @GetMapping
    public List<Postulacion> listar(@RequestParam(required = false) Long idVacante) {
        return service.listar(idVacante);
    }

    @GetMapping("/{id}")
    public Postulacion buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Postulacion registrar(@Valid @RequestBody PostulacionRequest datos) {
        return service.registrar(datos);
    }

    @PutMapping("/{id}")
    public Postulacion actualizar(@PathVariable Long id, @Valid @RequestBody PostulacionRequest datos) {
        return service.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
