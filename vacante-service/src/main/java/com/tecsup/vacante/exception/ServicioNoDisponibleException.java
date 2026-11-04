package com.tecsup.vacante.exception;

// Se lanza cuando otro microservicio no responde; se traduce a HTTP 503.
public class ServicioNoDisponibleException extends RuntimeException {

    public ServicioNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
