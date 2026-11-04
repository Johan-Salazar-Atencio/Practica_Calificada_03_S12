package com.tecsup.empresa.services;

import com.tecsup.empresa.model.Empresa;
import java.util.List;

// Contrato del servicio: el controlador depende de esta interfaz, no de la implementación.
public interface EmpresaService {

    List<Empresa> listar();

    Empresa buscarPorId(Long id);

    Empresa registrar(Empresa empresa);

    Empresa actualizar(Long id, Empresa empresa);

    void eliminar(Long id);
}
