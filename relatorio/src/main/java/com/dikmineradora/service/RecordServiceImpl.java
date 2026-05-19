package com.dikmineradora.service;

import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.dto.QuotationDto;
import com.dikmineradora.dto.RecordsDto;
import com.dikmineradora.entity.QuotationEntity;
import com.dikmineradora.entity.RecordsEntity;
import com.dikmineradora.repository.QuotationRepository;
import com.dikmineradora.repository.RecordsRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RecordServiceImpl implements RecordService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    RecordsRepository recordsRepository;

    @Override
    @WithSpan
    public void buildRecords(ProposalDto proposalDto) {

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);

        RecordsEntity recordsEntity = new RecordsEntity();
        recordsEntity.setDate(new Date());
        recordsEntity.setPriceTonne(proposalDto.getPriceTonne());
        recordsEntity.setProposalid(proposalDto.getProposalId());
        recordsEntity.setCostumer(proposalDto.getCostumer());
        recordsEntity.setLastDollarCottation(quotationEntities.get(0).getCurrentPrice());

        recordsRepository.persist(recordsEntity);


    }

    @Override
    @Transactional
    @WithSpan
    public void saveQuotation(QuotationDto quotationDto) {

        QuotationEntity quotationEntity = new QuotationEntity();

        quotationEntity.setDate(new Date());
        quotationEntity.setCurrentPrice(quotationDto.getCurrentPrice());

        quotationRepository.persist(quotationEntity);

    }

    @Override
    @WithSpan
    public List<RecordsDto> generateRecordsData() {

        List<RecordsDto> recordsList = new ArrayList<>();


        recordsRepository.findAll().stream().forEach(recordsEntity -> {

            recordsList.add(RecordsDto.builder()
                    .proposalId(recordsEntity.getProposalid())
                    .costumer(recordsEntity.getCostumer())
                    .priceTonne(recordsEntity.getPriceTonne())
                    .lastDollarCotation(recordsEntity.getLastDollarCottation())
                    .build());

        });

        return recordsList;

    }



//    //@Override
//    public ByteArrayInputStream generateCSVrecord() {
//
//        List<RecordsDto> recordsList = new ArrayList<>();
//
//
//        recordsRepository.findAll().list().forEach(recordsEntity -> {
//
//            recordsList.add(RecordsDto.builder()
//                    .proposalId(recordsEntity.getProposalid())
//                    .costumer(recordsEntity.getCostumer())
//                    .priceTonne(recordsEntity.getPriceTonne())
//                    .lastDollarCotation(recordsEntity.getLastDollarCottation())
//                    .build());
//
//        });
//
//
//        return CSVhelper.recordsToCsv(recordsList);
//    }



}
