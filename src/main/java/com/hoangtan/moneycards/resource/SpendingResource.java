package com.hoangtan.moneycards.resource;


import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.SpendingService;
import com.hoangtan.moneycards.service.model.SpendingDTO;
import io.swagger.annotations.Api;
import org.hibernate.annotations.Parameter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/spending")
@Api(tags = {"Spending"})
public class SpendingResource {

    @Inject
    private SpendingService spendingService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(SpendingDTO spendingDTO, @HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        SpendingDTO createdSpending = spendingService.create(spendingDTO, email);
        return Response.created(URI.create("spending/" + createdSpending.getId())).entity(createdSpending).status(Response.Status.CREATED).build();
    }
    @POST
    @Path("/spending-list")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response findByJarTypeAndUser(@QueryParam("jarType") int jarType, @HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        List<SpendingDTO>  spendingDTOList = spendingService.findByJarTypeAndUser(jarType, email);
        return Response.ok(spendingDTOList).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response findByUser(@HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        List<SpendingDTO> createdSpending = spendingService.findByUser(email);
        return Response.ok().entity(createdSpending).status(Response.Status.CREATED).build();
    }

}
