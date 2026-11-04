package com.tecsup.empresa.services;

import com.tecsup.empresa.dto.EmpresaRequest;
import com.tecsup.empresa.exception.BusinessRuleException;
import com.tecsup.empresa.exception.ResourceNotFoundException;
import com.tecsup.empresa.model.Empresa;
import com.tecsup.empresa.repository.EmpresaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository repository;

    // Inyección por constructor: Spring entrega el repositorio ya creado.
    public EmpresaServiceImpl(EmpresaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Empresa> listar() {
        return repository.findAll();
    }

    @Override
    public Empresa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la empresa con id " + id));
    }

    @Override
    public Empresa registrar(EmpresaRequest datos) {
        if (repository.existsByRuc(datos.ruc())) {
            throw new BusinessRuleException("Ya existe una empresa con el RUC " + datos.ruc());
        }
        return repository.save(copiar(new Empresa(), datos));
    }

    @Override
    public Empresa actualizar(Long id, EmpresaRequest datos) {
        Empresa actual = buscarPorId(id);
        // Solo se valida el RUC repetido si el usuario lo está cambiando.
        if (!actual.getRuc().equals(datos.ruc()) && repository.existsByRuc(datos.ruc())) {
            throw new BusinessRuleException("Ya existe una empresa con el RUC " + datos.ruc());
        }
        return repository.save(copiar(actual, datos));
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id)); // si no existe, lanza 404
    }

    // Pasa los datos del DTO a la entidad.
    private Empresa copiar(Empresa empresa, EmpresaRequest datos) {
        empresa.setRuc(datos.ruc());
        empresa.setRazonSocial(datos.razonSocial());
        empresa.setCorreo(datos.correo());
        empresa.setTelefono(datos.telefono());
        empresa.setEstado(datos.estado());
        return empresa;
    }
}
