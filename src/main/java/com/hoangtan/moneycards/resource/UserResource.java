package com.hoangtan.moneycards.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"ROLE_ADMIN"})
    @ApiOperation(value = "Create a user", authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Create a user successfully", response = Skill.class),
            @ApiResponse(code = 400, message = "Request sent to the server is invalid or corrupted"),
            @ApiResponse(code = 401, message = "Sign-in required"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Request cannot be fulfilled through browser due to server-side problems"),
    })
    public Response create(@ApiParam(value = "UserDTO to be created", required = true, name = "New user's info") User user) throws InputValidationException {
        User createdUser = userService.create(user);
        return Response.created(URI.create("users/" + createdUser.getId())).entity(createdUser).status(Response.Status.CREATED).build();
    }
}
