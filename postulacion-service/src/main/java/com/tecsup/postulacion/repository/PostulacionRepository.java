package com.tecsup.postulacion.repository;

import com.tecsup.postulacion.model.Postulacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {

    // Postulaciones de una vacante (consulta generada por el nombre del método).
    List<Postulacion> findByIdVacante(Long idVacante);
}
