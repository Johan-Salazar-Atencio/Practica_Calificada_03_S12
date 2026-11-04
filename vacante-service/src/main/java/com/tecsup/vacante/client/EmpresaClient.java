package com.tecsup.vacante.client;

import com.tecsup.vacante.exception.ServicioNoDisponibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

// Comunicación con empresa-service por REST: aquí vive todo lo relacionado con esa llamada.
@Component
public class EmpresaClient {

    private final RestTemplate restTemplate;
    private final String urlBase;

    public EmpresaClient(RestTemplate restTemplate, @Value("${servicios.empresa.url}") String urlBase) {
        this.restTemplate = restTemplate;
        this.urlBase = urlBase;
    }

    // Consulta GET /api/empresas/{id}: 200 = existe, 404 = no existe, sin respuesta = servicio caído.
    public boolean existe(Long idEmpresa) {
        try {
            restTemplate.getForObject(urlBase + "/api/empresas/" + idEmpresa, String.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (ResourceAccessException e) {
            throw new ServicioNoDisponibleException("empresa-service no está disponible en este momento");
        }
    }
}
