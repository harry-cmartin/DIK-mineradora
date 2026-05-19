package com.dikmineradora.service;

import com.dikmineradora.client.ProposalRestClient;
import com.dikmineradora.dto.ProposalDetailsDto;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService{

    @Inject
    @RestClient
    ProposalRestClient proposalRestClient;

    @Override
    @WithSpan
    public void createProposal(ProposalDetailsDto proposalDetailsDto) {

        try {
            proposalRestClient.createProposal(proposalDetailsDto);

        }catch (Exception e) {

            System.out.println(e.getMessage());
        }


    }

    @Override
    @WithSpan
    public void removeProposal(Long id) {

        try {
            proposalRestClient.removeProposal(id);

        }catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    @Override
    @WithSpan
    public ProposalDetailsDto findFullProposal(Long id) {



        return proposalRestClient.findProposal(id);


    }
}
