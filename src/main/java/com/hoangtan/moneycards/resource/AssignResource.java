package com.hoangtan.moneycards.resource;


import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.AssignService;
import com.hoangtan.moneycards.service.MoneyCardService;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/assign")
@Api(tags = {"Asign"})
public class AssignResource {

    @Inject
    private AssignService assignService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AssignDTO assignDTO, @HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        List<AssignDTO> createdAssign = assignService.create(assignDTO, email);
        return Response.created(URI.create("assign")).entity(createdAssign).status(Response.Status.CREATED).build();
    }


}
