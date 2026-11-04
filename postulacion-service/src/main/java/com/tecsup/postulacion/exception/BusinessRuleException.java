package com.tecsup.postulacion.exception;

// Se lanza al romper una regla del negocio (por ejemplo, vacante cerrada); se traduce a HTTP 400.
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String mensaje) {
        super(mensaje);
    }
}
