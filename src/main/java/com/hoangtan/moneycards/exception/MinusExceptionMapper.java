package com.hoangtan.moneycards.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class MinusExceptionMapper implements ExceptionMapper<MinusException> {

    private static final Logger logger = LogManager.getLogger(MinusExceptionMapper.class);

    @Override
    public Response toResponse(MinusException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getResponseBody().getErrorMessage());
        logger.info(logMessage);
        ResponseBody responseBody = e.getResponseBody();

        return Response.status(responseBody.getStatusCode())
                .entity(responseBody)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }
}