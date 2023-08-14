package com.hoangtan.moneycards.resource;


import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.AssignService;
import com.hoangtan.moneycards.service.MoneyCardService;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/assign")
public class AssignResource {

    @Inject
    private AssignService assignService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AssignDTO assignDTO) throws ResourceNotFoundException {
        AssignDTO createdAssign = assignService.create(assignDTO);
        return Response.created(URI.create("assign/" + createdAssign.getId())).entity(createdAssign).status(Response.Status.CREATED).build();
    }


}
