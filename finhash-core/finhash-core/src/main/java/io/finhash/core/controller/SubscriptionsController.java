package io.finhash.core.controller;

import io.finhash.core.service.BoCSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionsController {


    @Autowired
    BoCSubscriptionService subscriptionService;



    @GetMapping("/1")
    public String subsribe()  {
        try {
            subscriptionService.subscribe();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "ok";
    }
}
