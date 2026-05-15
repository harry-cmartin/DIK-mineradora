package com.dikmineradora.client;


import com.dikmineradora.dto.CurrentPriceDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/last")
@ApplicationScoped
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
public interface ClientPrice {

    @GET
    @Path("/{pair}")
    CurrentPriceDto getPricebyPair(@PathParam("pair") String pair);

}
