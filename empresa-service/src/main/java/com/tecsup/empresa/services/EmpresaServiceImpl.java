package com.tecsup.empresa.services;

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
    public Empresa registrar(Empresa empresa) {
        empresa.setId(null); // un registro nuevo nunca trae id
        return repository.save(empresa);
    }

    @Override
    public Empresa actualizar(Long id, Empresa datos) {
        Empresa actual = buscarPorId(id);
        actual.setRuc(datos.getRuc());
        actual.setRazonSocial(datos.getRazonSocial());
        actual.setCorreo(datos.getCorreo());
        actual.setTelefono(datos.getTelefono());
        actual.setEstado(datos.getEstado());
        return repository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id)); // si no existe, lanza 404
    }
}
