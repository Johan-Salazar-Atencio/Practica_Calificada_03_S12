package com.tecsup.postulacion.services;

import com.tecsup.postulacion.dto.PostulacionRequest;
import com.tecsup.postulacion.model.Postulacion;
import java.util.List;

// Contrato del servicio: el controlador depende de esta interfaz, no de la implementación.
public interface PostulacionService {

    // Si idVacante es null devuelve todas; si no, solo las de esa vacante.
    List<Postulacion> listar(Long idVacante);

    Postulacion buscarPorId(Long id);

    Postulacion registrar(PostulacionRequest datos);

    Postulacion actualizar(Long id, PostulacionRequest datos);

    void eliminar(Long id);
}
