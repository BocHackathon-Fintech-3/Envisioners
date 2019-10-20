package io.finhash.core.service;

import com.boc.client.model.AccessTokenResponse;
import com.boc.client.service.AuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TPPOAuth2Service {

    private final AuthorizationService authorisationService = new AuthorizationService();

    private static final Logger LOGGER = LoggerFactory.getLogger(TPPOAuth2Service.class);

    @Value("${boc.client-id}")
    String clientId;
    @Value("${boc.client-secret}")
    String clientSecret;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    BoCSubscriptionService boCSubscriptionService;

    @PostConstruct
    private void init() {
        //refreshAccessToken();
    }

    public void refreshAccessToken() {
        try {
            AccessTokenResponse response = authorisationService.getAccessToken(clientId, clientSecret, "client_credentials", "TPPOAuth2Security", "", "");
            LOGGER.info("Authentication success with payload:\n" + mapper.writeValueAsString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
