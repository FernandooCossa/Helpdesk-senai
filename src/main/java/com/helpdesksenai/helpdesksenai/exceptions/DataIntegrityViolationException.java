package com.helpdesksenai.helpdesksenai.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

    public DataIntegrityViolationException(String mensagem){
        super(mensagem);
    }
}
