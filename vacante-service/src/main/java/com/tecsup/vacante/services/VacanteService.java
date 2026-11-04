package com.tecsup.vacante.services;

import com.tecsup.vacante.dto.VacanteRequest;
import com.tecsup.vacante.model.Vacante;
import java.util.List;

// Contrato del servicio: el controlador depende de esta interfaz, no de la implementación.
public interface VacanteService {

    // Si idEmpresa es null devuelve todas; si no, solo las de esa empresa.
    List<Vacante> listar(Long idEmpresa);

    Vacante buscarPorId(Long id);

    Vacante registrar(VacanteRequest datos);

    Vacante actualizar(Long id, VacanteRequest datos);

    void eliminar(Long id);
}
