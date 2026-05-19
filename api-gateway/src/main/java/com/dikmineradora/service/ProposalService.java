package com.dikmineradora.service;

import com.dikmineradora.dto.ProposalDetailsDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;


public interface ProposalService {

    void createProposal(ProposalDetailsDto proposalDetailsDto);

    void removeProposal(@PathParam("id") Long id);

    ProposalDetailsDto findFullProposal(@PathParam("id") Long id);


}
