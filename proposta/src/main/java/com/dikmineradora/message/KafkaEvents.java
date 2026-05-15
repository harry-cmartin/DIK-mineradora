package com.dikmineradora.message;

import com.dikmineradora.dto.ProposalDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.logging.Logger;

@ApplicationScoped
public class KafkaEvents {


    private final Logger LOG = Logger.getLogger(KafkaEvents.class.getName());


    @Channel("proposal-topic")
    Emitter<ProposalDto> proposalRequestEmitter;


    public void sendNewKafkaEvent(ProposalDto proposal) {
        LOG.info("Sending new Kafka proposal event");
        proposalRequestEmitter.send(proposal).toCompletableFuture().join();
    }

}
