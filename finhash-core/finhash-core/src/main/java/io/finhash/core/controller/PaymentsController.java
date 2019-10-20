package io.finhash.core.controller;

import io.finhash.core.domain.Asset;
import io.finhash.core.domain.Payment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/payments")
@PreAuthorize("hasRole('Admin')")
public class PaymentsController {

    @GetMapping
    public Flux<Payment> findAll() {
        return Flux.empty();
    }
}
