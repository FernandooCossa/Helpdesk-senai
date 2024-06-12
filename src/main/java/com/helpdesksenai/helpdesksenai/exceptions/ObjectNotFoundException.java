package com.helpdesksenai.helpdesksenai.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

    public ObjectNotFoundException(String mensagem){
        super(mensagem);
    }

}
