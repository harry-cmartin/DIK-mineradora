package com.dikmineradora.controller;


import com.dikmineradora.service.RecordService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.ByteArrayInputStream;
import java.util.Date;

@Path("/api/records")
public class RecordsController {

    @Inject
    RecordService recordService;

    @GET
    @Path("/report")
    @RolesAllowed({"user", "manager"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateCSVrecord(){

        return Response.ok(recordService.generateCSVrecord(),
                        MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition",
                        "attachment; filename = "+ new Date() +"--oportunidades-venda.csv").build();

    }

    @GET
    @Path("/data")
    @RolesAllowed({"user" , "manager"})
    public Response getJSONRecords(){

        return Response.ok(recordService.generateRecordsData(), MediaType.APPLICATION_JSON).build();

    }

}
