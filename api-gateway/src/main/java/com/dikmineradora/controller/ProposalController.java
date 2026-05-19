package com.dikmineradora.controller;


import com.dikmineradora.dto.ProposalDetailsDto;
import com.dikmineradora.service.ProposalService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/trade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProposalController {

    @Inject
    ProposalService proposalService;


    @GET
    @Path("/{id}")
    @RolesAllowed({"user" ,"manager"})
    public Response getProposalbyId(@PathParam("id") long id){

        try{

            return Response.ok(proposalService.findFullProposal(id), MediaType.APPLICATION_JSON).build();

        }catch (Exception e){

            return Response.status(Response.Status.NOT_FOUND).build();
        }



    }


    @POST
    @RolesAllowed("costumer")
    public Response createProposal(ProposalDetailsDto proposal){

        proposalService.createProposal(proposal);

        return Response.ok().build();


    }

    @DELETE
    @RolesAllowed("manager")
    @Path("/remove/{id}")
    public Response removeProposal(@PathParam("id") long id){

        proposalService.removeProposal(id);

        return Response.ok().build();

    }


}
