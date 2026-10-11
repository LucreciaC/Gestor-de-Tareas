package com.hibernate.tareas.exception;

// Se traduce a 403 en GlobalExceptionHandler
public class AccesoDenegadoException extends RuntimeException {

    public AccesoDenegadoException(String mensaje) {
        super(mensaje);
    }
}
