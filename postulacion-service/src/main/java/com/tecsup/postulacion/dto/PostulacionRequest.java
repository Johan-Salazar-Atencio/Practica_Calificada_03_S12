package com.tecsup.postulacion.dto;

import com.tecsup.postulacion.model.EstadoPostulacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// Datos de entrada validados de una postulación.
public record PostulacionRequest(

        @NotNull(message = "El id de la vacante es obligatorio")
        @Positive(message = "El id de la vacante debe ser positivo")
        Long idVacante,

        @NotBlank(message = "El nombre del postulante es obligatorio")
        @Size(max = 120, message = "El nombre no puede pasar de 120 caracteres")
        String nombrePostulante,

        // Requisito del laboratorio: el correo no puede venir vacío y debe tener formato válido.
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,

        @NotNull(message = "La fecha es obligatoria")
        @PastOrPresent(message = "La fecha no puede ser futura")
        LocalDate fecha,

        // Opcional: si no se envía, una postulación nueva queda PENDIENTE.
        EstadoPostulacion estado
) {
}
