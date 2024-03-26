package com.etienne.gestionnaireBacklog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JoueurJeuIntrouvableException extends RuntimeException{
    public JoueurJeuIntrouvableException (String msg){
        super(msg);
    }
}
