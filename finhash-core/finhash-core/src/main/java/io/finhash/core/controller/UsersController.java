package io.finhash.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.finhash.core.domain.BankDetails;
import io.finhash.core.domain.User;
import io.finhash.core.model.BoCAuthCode;
import io.finhash.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/users")
//@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'OWNER')")
public class UsersController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public Mono<User> self(Authentication auth) {
        return userRepository.findByUsername(String.valueOf(auth.getPrincipal()));
    }

    @PostMapping("/linkAccount")
    public Mono<Boolean> linkAccount(Authentication auth, @RequestBody BoCAuthCode boCAuthCode) {
        if (auth != null) {
            userRepository.findByUsername(String.valueOf(auth.getPrincipal()))
                    .map(user -> {
                        user.setBankDetails(new BankDetails(boCAuthCode.getCode(), "IBAN:213423140871", 0.0));
                        return user;
                    }).subscribe(user ->
                    userRepository.save(user).subscribe()
            );
            return Mono.just(true);
        }
        return Mono.just(false);
    }
}
