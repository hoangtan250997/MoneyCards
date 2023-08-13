package com.hoangtan.moneycards.resource;


import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.IncomeSourceService;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/income-source")
public class IncomeSourceResource {

    @Inject
    private IncomeSourceService incomeSourceService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(IncomeSourceDTO incomeSourceDTO, @HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        IncomeSourceDTO createdIncomeSource = incomeSourceService.create(incomeSourceDTO, email);
        return Response.created(URI.create("income-source/" + createdIncomeSource.getId())).entity(createdIncomeSource).status(Response.Status.CREATED).build();
    }


}
