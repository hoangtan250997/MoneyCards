package com.hoangtan.moneycards.resource;


import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.service.MoneyCardService;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/money-card")
public class MoneyCardResource {

    @Inject
    private MoneyCardService moneyCardService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(MoneyCardDTO moneyCardDTO, @HeaderParam("Authorization") String authorization) throws ResourceNotFoundException {
        String email = jwtUtils.getEmailFromToken(authorization);
        MoneyCardDTO createdMoneyCard = moneyCardService.create(moneyCardDTO, email);
        return Response.created(URI.create("money-card/" + createdMoneyCard.getId())).entity(createdMoneyCard).status(Response.Status.CREATED).build();
    }


}
