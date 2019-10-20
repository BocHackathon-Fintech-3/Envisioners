package io.finhash.core.service;

import com.boc.client.api.ApiConfiguration;
import com.boc.client.model.AccountView;
import com.boc.client.model.CreateSubscriptionRequest;
import com.boc.client.model.CreateSubscriptionResponse;
import com.boc.client.model.PaymentView;
import com.boc.client.service.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class BoCSubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoCSubscriptionService.class);
    private final SubscriptionService subscriptionService = new SubscriptionService();

    @Value("${boc.subscription-id:N/A}")
    String subscriptionId;

    @Autowired
    ObjectMapper mapper;

    public CreateSubscriptionResponse subscribe() throws Exception {
        String journeyId = ApiConfiguration.JOURNEY_ID;
        String appName = "FinHash";
        String originUserId = ApiConfiguration.ORIGIN_USER_ID;
        String tppId = ApiConfiguration.TPP_ID;

        // TODO: Mock data, provide option for user input
        PaymentView paymentView = new PaymentView();
        paymentView.setAmount(new BigDecimal(ApiConfiguration.AMOUNT));
        paymentView.setCurrency(ApiConfiguration.CURRENCY);
        paymentView.setLimit(new BigDecimal(ApiConfiguration.LIMIT));

        AccountView accountView = new AccountView();
        accountView.setBalance(ApiConfiguration.BALANCE);
        accountView.setTransactionHistory(ApiConfiguration.TRANASACTION_HISTORY);
        accountView.setDetails(ApiConfiguration.DETAILS);
        accountView.setCheckFundsAvailability(ApiConfiguration.CHECK_FUNDS_AVAILABILITY);

        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setAccounts(accountView);
        request.setPayments(paymentView);

        CreateSubscriptionResponse response = subscriptionService.createSubscription(
                request,
                journeyId,
                appName,
                originUserId,
                tppId,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        LOGGER.info(mapper.writeValueAsString(response));

        return response;
    }

    public boolean isSubscribed() {
        return !this.subscriptionId.isBlank() && !this.subscriptionId.equals("N/A");
    }
}
