package io.finhash.core.stream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @KafkaListener(topics = "${app.assetengine.topic-sub}")
    public void receive(@Payload String message,
                        @Headers MessageHeaders headers) {
        LOGGER.info("received message='{}'", message);
        headers.keySet().forEach(key -> LOGGER.info("{}: {}", key, headers.get(key)));
    }

}