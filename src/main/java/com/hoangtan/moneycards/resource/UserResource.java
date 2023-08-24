package com.hoangtan.moneycards.resource;

import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.UserService;
import com.hoangtan.moneycards.service.model.UserDTO;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/users")
@Api(tags = {"Users"})
public class UserResource {

    @Inject
    private UserService userService;
    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(UserDTO user) throws InputValidationException {
        UserDTO createdUser = userService.create(user);
        return Response.created(URI.create("users/" + createdUser.getId())).entity(createdUser).status(Response.Status.CREATED).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        return Response.ok().entity(userService.findAll()).build();
    }

    @GET
    @Path("balance")
    @Produces({MediaType.APPLICATION_JSON})
    public Response returnAllMoney(@HeaderParam("Authorization") String authorization) {
        String email = jwtUtils.getEmailFromToken(authorization);
        return Response.ok().entity(userService.returnAllMoney(email)).build();
    }
}
