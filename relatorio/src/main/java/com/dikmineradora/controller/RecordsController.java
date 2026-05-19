package com.dikmineradora.controller;


import com.dikmineradora.dto.RecordsDto;
import com.dikmineradora.service.RecordService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Date;
import java.util.List;

@Path("/api/records")
@Authenticated
public class RecordsController {

    @Inject
    RecordService recordService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/data")
    @RolesAllowed("manager")
    public List<RecordsDto> generateReport(){

        return recordService.generateRecordsData();

    }


}
