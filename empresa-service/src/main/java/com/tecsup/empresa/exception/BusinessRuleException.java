package com.tecsup.empresa.exception;

// Se lanza al romper una regla del negocio (por ejemplo, RUC repetido); se traduce a HTTP 400.
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String mensaje) {
        super(mensaje);
    }
}
