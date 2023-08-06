package com.hoangtan.moneycards.security.resource;

import com.axonactive.agileskills.base.exception.AuthorizationException;
import com.axonactive.agileskills.base.exception.InputValidationException;
import com.axonactive.agileskills.base.security.controller.model.JwtRequest;
import com.axonactive.agileskills.base.security.service.dto.JwtResponse;
import com.axonactive.agileskills.user.service.UserService;
import com.hoangtan.moneycards.security.service.AuthenticationService;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Api(tags = {"Auth"})
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
    @ApiOperation(value = "Login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully logged in", response = JwtResponse.class),
            @ApiResponse(code = 400, message = "Request sent to the server is invalid or corrupted"),
            @ApiResponse(code = 500, message = "Request cannot be fulfilled through browser due to server-side problems"),
    })
    public Response getJwtResponse(@ApiParam(value = "Login",
            required = true, name = "User login's info") JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        JwtResponse jwtResponse = jwtUtils.generateJwtResponse(jwtRequest);
        return Response.ok(jwtResponse).build();
    }
}
