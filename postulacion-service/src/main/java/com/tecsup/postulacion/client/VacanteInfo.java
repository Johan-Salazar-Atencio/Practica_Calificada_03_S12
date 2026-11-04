package com.tecsup.postulacion.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

// Solo los datos de la vacante que este servicio necesita; el resto del JSON se ignora.
@JsonIgnoreProperties(ignoreUnknown = true)
public record VacanteInfo(Long id, String estado, LocalDate fechaCierre) {
}
