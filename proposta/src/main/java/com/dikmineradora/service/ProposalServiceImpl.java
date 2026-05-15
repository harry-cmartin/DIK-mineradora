package com.dikmineradora.service;

import com.dikmineradora.dto.ProposalDetailsDto;
import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.entity.ProposalEntity;
import com.dikmineradora.mapper.ProposalMapper;
import com.dikmineradora.message.KafkaEvents;
import com.dikmineradora.repository.ProposalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ProposalServiceImpl implements ProposalService{

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvents kafkaMessages;

    @Inject
    ProposalMapper proposalMapper;

    @Override
    public ProposalDetailsDto findFullProposal(Long id) {

        ProposalEntity prop = proposalRepository.findById(id);

        return ProposalDetailsDto.builder()
                .proposalId(prop.getId())
                .costumer(prop.getCostumer())
                .priceTonne(prop.getPriceTonne())
                .tonnes(prop.getTonnes())
                .country(prop.getCountry())
                .proposalValidityDays(prop.getProposalValidityDays())
                .build();

    }

    @Override
    @Transactional
    public void createProposal(ProposalDetailsDto proposalDetailsDto) {

        ProposalDto proposal = buildandSaveProposal(proposalDetailsDto);

        kafkaMessages.sendNewKafkaEvent(proposal);

    }

    @Override
    @Transactional
    public void removeProposal(Long id) {

        proposalRepository.deleteById(id);

    }


    @Transactional
    private ProposalDto buildandSaveProposal(ProposalDetailsDto proposalDetailsDto) {


        try {

            ProposalEntity prop = proposalMapper.toEntity(proposalDetailsDto);

            if(prop != null){

                proposalRepository.persist(prop);

            }

            return ProposalDto.builder()
                    .proposalId(proposalRepository.findCostumer(prop.getCostumer()).get().getId())
                    .costumer(proposalDetailsDto.getCostumer())
                    .priceTonne(proposalDetailsDto.getPriceTonne())
                    .build();

        }catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar a proposta");

        }







    }
}
