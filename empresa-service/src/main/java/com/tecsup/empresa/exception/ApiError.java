package com.tecsup.empresa.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Formato único de todas las respuestas de error de la API.
public record ApiError(
        LocalDateTime fecha,
        int estado,
        String error,
        String mensaje,
        Map<String, String> detalles // errores por campo (solo en validaciones)
) {
}
