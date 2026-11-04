package com.tecsup.empresa.repository;

import com.tecsup.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ya trae guardar, buscar, listar y eliminar; solo se añaden consultas propias.
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    // Spring genera la consulta a partir del nombre del método.
    boolean existsByRuc(String ruc);
}
