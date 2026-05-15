package com.dikmineradora.controller;


import com.dikmineradora.dto.ProposalDetailsDto;
import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.service.ProposalService;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/proposal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ProposalController {

    @Inject
    JsonWebToken jwt;

    @Inject
    ProposalService proposalService;

    @POST
    @RolesAllowed("costumer")
    public Response createProposal (ProposalDetailsDto proposal){

        Log.info("Recebendo nova proposta: " );

        try {
            proposalService.createProposal(proposal);

            return Response.ok(Response.Status.CREATED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        }

    }


    @GET
    @Path("/{id}")
    @RolesAllowed({"user","manager"})
    public ProposalDetailsDto findProposal(@PathParam("id") long id){

        try {

            return proposalService.findFullProposal(id);

        } catch (Exception e) {

            throw new RuntimeException("Erro ao buscar a proposta");
        }

    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id){

        try {

            proposalService.removeProposal(id);

            return Response.ok(Response.Status.ACCEPTED).build();

        } catch (Exception e) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }



}
