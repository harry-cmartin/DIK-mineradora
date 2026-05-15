package com.dikmineradora.repository;

import com.dikmineradora.entity.ProposalEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<ProposalEntity> {

    public Optional<ProposalEntity> findCostumer(String costumer) {

        return Optional.of(find("costumer", costumer).firstResult());


    }
}
