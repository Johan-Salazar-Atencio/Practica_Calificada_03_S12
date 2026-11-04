package com.tecsup.vacante.services;

import com.tecsup.vacante.client.EmpresaClient;
import com.tecsup.vacante.dto.VacanteRequest;
import com.tecsup.vacante.exception.ResourceNotFoundException;
import com.tecsup.vacante.model.Vacante;
import com.tecsup.vacante.repository.VacanteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VacanteServiceImpl implements VacanteService {

    private final VacanteRepository repository;
    private final EmpresaClient empresaClient;

    public VacanteServiceImpl(VacanteRepository repository, EmpresaClient empresaClient) {
        this.repository = repository;
        this.empresaClient = empresaClient;
    }

    @Override
    public List<Vacante> listar(Long idEmpresa) {
        return idEmpresa == null ? repository.findAll() : repository.findByIdEmpresa(idEmpresa);
    }

    @Override
    public Vacante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la vacante con id " + id));
    }

    @Override
    public Vacante registrar(VacanteRequest datos) {
        validarEmpresa(datos.idEmpresa());
        return repository.save(copiar(new Vacante(), datos));
    }

    @Override
    public Vacante actualizar(Long id, VacanteRequest datos) {
        Vacante actual = buscarPorId(id);
        validarEmpresa(datos.idEmpresa());
        return repository.save(copiar(actual, datos));
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }

    // Regla: no hay llave foránea entre bases, así que se consulta a empresa-service por REST.
    private void validarEmpresa(Long idEmpresa) {
        if (!empresaClient.existe(idEmpresa)) {
            throw new ResourceNotFoundException("No existe la empresa con id " + idEmpresa);
        }
    }

    private Vacante copiar(Vacante vacante, VacanteRequest datos) {
        vacante.setTitulo(datos.titulo());
        vacante.setDescripcion(datos.descripcion());
        vacante.setArea(datos.area());
        vacante.setFechaCierre(datos.fechaCierre());
        vacante.setIdEmpresa(datos.idEmpresa());
        vacante.setEstado(datos.estado());
        return vacante;
    }
}
