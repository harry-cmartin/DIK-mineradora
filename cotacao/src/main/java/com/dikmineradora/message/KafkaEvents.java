package com.dikmineradora.message;

import com.dikmineradora.dto.QuotationDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.logging.Logger;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = Logger.getLogger(KafkaEvents.class.getName());


    @Channel("quotation-topic")
    Emitter<QuotationDto> quotationRequestEmitter;


    public void sendNewKafkaEvent(QuotationDto quotation) {
        LOG.info("Sending new Kafka event");
        quotationRequestEmitter.send(quotation).toCompletableFuture().join();
    }

}
