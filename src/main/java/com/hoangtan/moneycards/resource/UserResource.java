package com.hoangtan.moneycards.resource;

import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.service.UserService;
import com.hoangtan.moneycards.service.model.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(UserDTO user) throws InputValidationException {
        UserDTO createdUser = userService.create(user);
        return Response.created(URI.create("users/" + createdUser.getId())).entity(createdUser).status(Response.Status.CREATED).build();
    }
}
