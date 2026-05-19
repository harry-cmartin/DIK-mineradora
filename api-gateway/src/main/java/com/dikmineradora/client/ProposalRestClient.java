package com.dikmineradora.client;


import com.dikmineradora.dto.ProposalDetailsDto;
import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("api/proposal")
@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProposalRestClient {

    @GET
    @Path("{id}")
    ProposalDetailsDto findProposal(@PathParam("id") long id);

    @POST
    Response createProposal(ProposalDetailsDto proposal);

    @DELETE
    @Path("{id}")
    Response removeProposal(@PathParam("id") long id);


}
