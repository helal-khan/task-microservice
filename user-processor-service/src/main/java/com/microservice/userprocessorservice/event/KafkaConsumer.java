package com.microservice.userprocessorservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "USER_EVENTS")
    public void listen(@Payload UserEvent userEvent){
        log.info("Received Data: {}", userEvent.toString());
    }
}
