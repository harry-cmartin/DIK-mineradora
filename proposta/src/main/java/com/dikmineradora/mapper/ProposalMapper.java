package com.dikmineradora.mapper;

import com.dikmineradora.dto.ProposalDetailsDto;
import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.entity.ProposalEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;

@ApplicationScoped
public class ProposalMapper {

    public ProposalEntity toEntity(ProposalDetailsDto prop ) {

        try {

            ProposalEntity propEntity = new ProposalEntity();

            propEntity.setCostumer(prop.getCostumer());
            propEntity.setPriceTonne(prop.getPriceTonne());
            propEntity.setTonnes(prop.getTonnes());
            propEntity.setCountry(prop.getCountry());
            propEntity.setProposalValidityDays(prop.getProposalValidityDays());
            propEntity.setCreated(new Date());

            return propEntity;


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

}
