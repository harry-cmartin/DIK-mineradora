package com.dikmineradora.service;

import com.dikmineradora.client.RecordRestClient;
import com.dikmineradora.dto.ProposalDetailsDto;
import com.dikmineradora.dto.RecordsDto;
import com.dikmineradora.utils.CSVhelper;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public class RecordServiceImpl implements RecordService{

    @Inject
    @RestClient
    RecordRestClient recordRestClient;


    @Override
    @WithSpan
    public ByteArrayInputStream generateCSVrecord() {

        try {

            List<RecordsDto> records;

            records = recordRestClient.requestRecords();

            return CSVhelper.recordsToCsv(records) ;

        }catch ( Exception e){

            System.out.println(e.getMessage());

        }

        return null;
    }

    @Override
    @WithSpan
    public List<RecordsDto> generateRecordsData() {

        return recordRestClient.requestRecords();
    }
}
