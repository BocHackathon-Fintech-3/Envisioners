package io.finhash.core.controller;

import io.finhash.core.domain.Asset;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/assets")
@PreAuthorize("hasRole('Admin')")
public class AssetsController {

    @GetMapping
    public Flux<Asset> findAll() {
        return Flux.empty();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public Mono<Asset> findOne(@PathVariable("id") String id) {
        return Mono.empty();
    }

    @PostMapping("/{id}")
    public Mono<Asset> save(@RequestBody Asset asset) {
        return Mono.just(asset);
    }

    @PutMapping("/{id}")
    public Mono<Asset> replace(@RequestBody Asset asset) {
        return Mono.just(asset);
    }

    @PatchMapping("/{id}")
    public Mono<Asset> update(@RequestBody Asset asset) {
        return Mono.just(asset);
    }

    @Deprecated
    @PostMapping("/{id}/assign/{username}")
    public Mono<Asset> assign(@RequestBody Asset asset) {
        return Mono.just(asset);
    }


    @DeleteMapping()
    @PreAuthorize("hasRole('Admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable("id") String id) {
    }


}
