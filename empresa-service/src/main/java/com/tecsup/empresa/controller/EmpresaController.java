package com.tecsup.empresa.controller;

import com.tecsup.empresa.model.Empresa;
import com.tecsup.empresa.services.EmpresaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// Capa HTTP: solo recibe la petición y delega en el servicio.
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Empresa> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Empresa buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Empresa registrar(@RequestBody Empresa empresa) {
        return service.registrar(empresa);
    }

    @PutMapping("/{id}")
    public Empresa actualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        return service.actualizar(id, empresa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
