package com.tecsup.vacante.dto;

import com.tecsup.vacante.model.EstadoVacante;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// Datos de entrada validados de una vacante.
public record VacanteRequest(

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 120, message = "El título no puede pasar de 120 caracteres")
        String titulo,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 500, message = "La descripción no puede pasar de 500 caracteres")
        String descripcion,

        @NotBlank(message = "El área es obligatoria")
        @Size(max = 80, message = "El área no puede pasar de 80 caracteres")
        String area,

        // Requisito del laboratorio: la fecha de cierre debe venir informada (y no ser pasada).
        @NotNull(message = "La fecha de cierre es obligatoria")
        @FutureOrPresent(message = "La fecha de cierre no puede ser anterior a hoy")
        LocalDate fechaCierre,

        @NotNull(message = "El id de la empresa es obligatorio")
        @Positive(message = "El id de la empresa debe ser positivo")
        Long idEmpresa,

        @NotNull(message = "El estado es obligatorio (ABIERTA o CERRADA)")
        EstadoVacante estado
) {
}
