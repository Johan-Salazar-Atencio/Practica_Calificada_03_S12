package com.tecsup.postulacion.services;

import com.tecsup.postulacion.client.VacanteClient;
import com.tecsup.postulacion.client.VacanteInfo;
import com.tecsup.postulacion.dto.PostulacionRequest;
import com.tecsup.postulacion.exception.BusinessRuleException;
import com.tecsup.postulacion.exception.ResourceNotFoundException;
import com.tecsup.postulacion.model.EstadoPostulacion;
import com.tecsup.postulacion.model.Postulacion;
import com.tecsup.postulacion.repository.PostulacionRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository repository;
    private final VacanteClient vacanteClient;

    public PostulacionServiceImpl(PostulacionRepository repository, VacanteClient vacanteClient) {
        this.repository = repository;
        this.vacanteClient = vacanteClient;
    }

    @Override
    public List<Postulacion> listar(Long idVacante) {
        return idVacante == null ? repository.findAll() : repository.findByIdVacante(idVacante);
    }

    @Override
    public Postulacion buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la postulación con id " + id));
    }

    @Override
    public Postulacion registrar(PostulacionRequest datos) {
        validarVacanteAbierta(datos.idVacante());
        Postulacion nueva = copiar(new Postulacion(), datos);
        if (nueva.getEstado() == null) {
            nueva.setEstado(EstadoPostulacion.PENDIENTE); // estado por defecto
        }
        return repository.save(nueva);
    }

    @Override
    public Postulacion actualizar(Long id, PostulacionRequest datos) {
        Postulacion actual = buscarPorId(id);
        // Cambiar el estado de una postulación vieja debe seguir permitido aunque la vacante ya cerró;
        // la vacante solo se vuelve a validar si la postulación se mueve a otra.
        if (!actual.getIdVacante().equals(datos.idVacante())) {
            validarVacanteAbierta(datos.idVacante());
        }
        EstadoPostulacion estadoAnterior = actual.getEstado();
        copiar(actual, datos);
        if (actual.getEstado() == null) {
            actual.setEstado(estadoAnterior); // si no se envía estado, se conserva el actual
        }
        return repository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }

    // Reglas: la vacante debe existir (404), estar ABIERTA y no haber vencido (400).
    private void validarVacanteAbierta(Long idVacante) {
        VacanteInfo vacante = vacanteClient.buscar(idVacante)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la vacante con id " + idVacante));
        if (!"ABIERTA".equals(vacante.estado())) {
            throw new BusinessRuleException("La vacante " + idVacante + " está cerrada");
        }
        if (vacante.fechaCierre().isBefore(LocalDate.now())) {
            throw new BusinessRuleException("La vacante " + idVacante + " ya superó su fecha de cierre");
        }
    }

    private Postulacion copiar(Postulacion postulacion, PostulacionRequest datos) {
        postulacion.setIdVacante(datos.idVacante());
        postulacion.setNombrePostulante(datos.nombrePostulante());
        postulacion.setCorreo(datos.correo());
        postulacion.setFecha(datos.fecha());
        postulacion.setEstado(datos.estado());
        return postulacion;
    }
}
