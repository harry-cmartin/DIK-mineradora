package com.dikmineradora.service;


import com.dikmineradora.client.ClientPrice;
import com.dikmineradora.dto.CurrentPriceDto;
import com.dikmineradora.dto.QuotationDto;
import com.dikmineradora.entity.QuotationEntity;
import com.dikmineradora.message.KafkaEvents;
import com.dikmineradora.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    ClientPrice clientPrice;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    public void getQuotation() {

        CurrentPriceDto currentPriceinfo = clientPrice.getPricebyPair("USD-BRL");

        if(updateCurrentInfoPrice(currentPriceinfo)) {

            kafkaEvents.sendNewKafkaEvent(QuotationDto
                    .builder()
                    .currentPrice(new BigDecimal(currentPriceinfo.getUSDBRL().getBid()))
                    .date(new Date())
                    .build());


        }

    }


    private boolean updateCurrentInfoPrice (CurrentPriceDto currentPrice){

        BigDecimal actualPrice = new BigDecimal(currentPrice.getUSDBRL().getBid());
        System.out.println("Actual Price: " + actualPrice);
        boolean updatedPrice = false;

        List<QuotationEntity> quotations = quotationRepository.findAll().list();

        if(quotations.isEmpty()) {


            updatedPrice =true;
            saveQuotation(currentPrice);

        }else {


            QuotationEntity lastQuotation = quotations.get(quotations.size() - 1);

            if(lastQuotation.getCurrentPrice().compareTo(actualPrice) < 0) {

                updatedPrice = true;
                saveQuotation(currentPrice);
            }

        }

        return updatedPrice;

    }

    private void saveQuotation(CurrentPriceDto price) {

        QuotationEntity quote = new QuotationEntity();

        quote.setCurrentPrice(new BigDecimal(price.getUSDBRL().getBid()));
        quote.setDate(new Date());
        quote.setPair("USD-BRL");
        quote.setPctChange(price.getUSDBRL().getPctChange());

        quotationRepository.persist(quote);

    }




}
