package io.finhash.core;

import io.finhash.core.stream.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DummyScheduler {

    @Autowired
    EventProducer eventProducer;

    @Scheduled(fixedDelay = 60000)
    public void doSomething(){
        System.out.printf("Sending message..");
     eventProducer.send("hello!");
    }
}
