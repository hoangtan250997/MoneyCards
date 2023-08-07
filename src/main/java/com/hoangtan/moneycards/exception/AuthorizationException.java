package com.hoangtan.moneycards.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@ApplicationException
public class AuthorizationException extends Exception {

    @Getter
    private final transient ResponseBody responseBody;

    public AuthorizationException(Response.Status status, String keyMessage, String message) {
        this.responseBody = new ResponseBody(status, keyMessage, message);
    }
}
