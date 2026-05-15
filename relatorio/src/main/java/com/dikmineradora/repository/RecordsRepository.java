package com.dikmineradora.repository;

import com.dikmineradora.dto.RecordsDto;
import com.dikmineradora.entity.RecordsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecordsRepository implements PanacheRepository<RecordsEntity> {
}
