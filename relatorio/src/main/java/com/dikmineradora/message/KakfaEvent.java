package com.dikmineradora.message;

import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.dto.QuotationDto;
import com.dikmineradora.service.RecordService;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class KakfaEvent {

    private final Logger LOG = LoggerFactory.getLogger(KakfaEvent.class);

    @Inject
    RecordService recordService;

    @Incoming("proposal-topic")
    @Transactional
    public void receiveProposal(ProposalDto proposal){
        LOG.info("RECEBENDO PROPOSTA DO KAFKA");
        recordService.buildRecords(proposal);

    }


    @Incoming("quotation-topic")
    @Blocking
    public void receiveQuotation(QuotationDto quotation){
        LOG.info("RECEBENDO QUOTACAO DO KAFKA");
        recordService.saveQuotation(quotation);

    }

}
