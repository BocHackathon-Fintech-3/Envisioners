package io.finhash.core.controller;

import io.finhash.core.auth.AuthRequest;
import io.finhash.core.auth.AuthResponse;
import io.finhash.core.auth.JWTUtil;
import io.finhash.core.auth.PBKDF2Encoder;
import io.finhash.core.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        System.out.printf("Trying to login user: "+ ar.getUsername());

        return userRepository.findByUsername(ar.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }



    @PreAuthorize("hasRole('TENANT')")
    @GetMapping("/tenant")
    public Mono<String> tenant() {
        return Mono.just("tenant access");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Mono<String> admin() {
        return Mono.just("tenant access");
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/owner")
    public Mono<String> owner() {
        return Mono.just("tenant access");
    }


}
