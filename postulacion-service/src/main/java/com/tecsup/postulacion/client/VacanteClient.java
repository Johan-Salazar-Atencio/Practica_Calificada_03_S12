package com.tecsup.postulacion.client;

import com.tecsup.postulacion.exception.ServicioNoDisponibleException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

// Comunicación con vacante-service por REST: aquí vive todo lo relacionado con esa llamada.
@Component
public class VacanteClient {

    private final RestTemplate restTemplate;
    private final String urlBase;

    public VacanteClient(RestTemplate restTemplate, @Value("${servicios.vacante.url}") String urlBase) {
        this.restTemplate = restTemplate;
        this.urlBase = urlBase;
    }

    // Consulta GET /api/vacantes/{id}: vacía si no existe; lanza 503 si el servicio no responde.
    public Optional<VacanteInfo> buscar(Long idVacante) {
        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(urlBase + "/api/vacantes/" + idVacante, VacanteInfo.class));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (ResourceAccessException e) {
            throw new ServicioNoDisponibleException("vacante-service no está disponible en este momento");
        }
    }
}
