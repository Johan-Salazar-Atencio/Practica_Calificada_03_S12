package com.tecsup.vacante.exception;

// Se lanza cuando un registro buscado por ID no existe; luego se traduce a HTTP 404.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
