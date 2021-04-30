package com.DevMark.Weeks_View.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WvAuthException extends RuntimeException {

    public WvAuthException(String message){
        super(message);
    }
}
