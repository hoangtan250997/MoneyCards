package com.hoangtan.moneycards.security.resource;

import com.axonactive.agileskills.base.exception.AuthorizationException;
import com.axonactive.agileskills.base.exception.InputValidationException;
import com.axonactive.agileskills.base.security.controller.model.JwtRequest;
import com.axonactive.agileskills.base.security.service.dto.JwtResponse;
import com.axonactive.agileskills.user.service.UserService;
import com.hoangtan.moneycards.exception.AuthorizationException;
import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.security.resource.model.JwtRequest;
import com.hoangtan.moneycards.security.service.AuthenticationService;
import com.hoangtan.moneycards.security.service.dto.JwtResponse;
import com.hoangtan.moneycards.security.utility.JwtUtils;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

    @Inject
    private JwtUtils jwtUtils;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})

    public Response getJwtResponse(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        JwtResponse jwtResponse = jwtUtils.generateJwtResponse(jwtRequest);
        return Response.ok(jwtResponse).build();
    }
}
