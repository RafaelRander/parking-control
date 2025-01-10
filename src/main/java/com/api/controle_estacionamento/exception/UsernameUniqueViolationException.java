package com.api.controle_estacionamento.exception;

public class UsernameUniqueViolationException extends RuntimeException{
    public UsernameUniqueViolationException(String message) {
        super(message);
    }
}
