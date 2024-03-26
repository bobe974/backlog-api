package com.etienne.gestionnaireBacklog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JoueurIntrouvableException extends RuntimeException{
    public JoueurIntrouvableException(String msg){
        super(msg);
    }
}
