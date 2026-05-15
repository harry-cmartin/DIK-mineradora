package com.dikmineradora.service;

import com.dikmineradora.dto.ProposalDetailsDto;

public interface ProposalService {

    ProposalDetailsDto findFullProposal(Long id);

    void createProposal(ProposalDetailsDto proposalDetailsDto);

    void removeProposal(Long id);

}
