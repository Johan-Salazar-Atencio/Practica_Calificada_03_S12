package com.tecsup.vacante.repository;

import com.tecsup.vacante.model.Vacante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacanteRepository extends JpaRepository<Vacante, Long> {

    // Vacantes de una empresa (consulta generada por el nombre del método).
    List<Vacante> findByIdEmpresa(Long idEmpresa);
}
