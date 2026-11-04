package com.tecsup.postulacion.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

// Centraliza el manejo de errores: los controladores no necesitan try/catch.
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 404: el ID buscado no existe.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> noEncontrado(ResourceNotFoundException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    // 400: se rompió una regla del negocio.
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> reglaNegocio(BusinessRuleException ex) {
        return responder(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    // 503: vacante-service no responde, así que no se puede validar la vacante.
    @ExceptionHandler(ServicioNoDisponibleException.class)
    public ResponseEntity<ApiError> servicioCaido(ServicioNoDisponibleException ex) {
        return responder(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), null);
    }

    // 400: falló alguna anotación del DTO (@NotBlank, @Email...); se devuelve el detalle por campo.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> datosInvalidos(MethodArgumentNotValidException ex) {
        Map<String, String> detalles = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> detalles.put(e.getField(), e.getDefaultMessage()));
        return responder(HttpStatus.BAD_REQUEST, "Hay datos inválidos en la solicitud", detalles);
    }

    // 400: JSON mal escrito o valor de enum que no existe.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> jsonInvalido(HttpMessageNotReadableException ex) {
        return responder(HttpStatus.BAD_REQUEST,
                "El cuerpo de la solicitud no es válido o tiene un valor no permitido", null);
    }

    // 400: el ID de la URL no es un número (por ejemplo /api/postulaciones/abc).
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> tipoIncorrecto(MethodArgumentTypeMismatchException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El valor de '" + ex.getName() + "' no es válido", null);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> rutaInexistente(NoResourceFoundException ex) {
        return responder(HttpStatus.NOT_FOUND, "La ruta solicitada no existe", null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> metodoNoPermitido(HttpRequestMethodNotSupportedException ex) {
        return responder(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP no permitido en esta ruta", null);
    }

    // 500: cualquier error no previsto; el detalle va al log y no se muestra al cliente.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> errorGeneral(Exception ex) {
        log.error("Error interno", ex);
        return responder(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno en el servidor", null);
    }

    private ResponseEntity<ApiError> responder(HttpStatus estado, String mensaje, Map<String, String> detalles) {
        ApiError cuerpo = new ApiError(LocalDateTime.now(), estado.value(), estado.getReasonPhrase(), mensaje, detalles);
        return ResponseEntity.status(estado).body(cuerpo);
    }
}
