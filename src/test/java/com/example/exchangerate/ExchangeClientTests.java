package com.example.exchangerate;

import com.example.exchangerate.client.ExchangeClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock
class ExchangeClientTests {

    @Autowired
    private ExchangeClient exchangeClient;

    @Value("${exchange.api}")
    private String exchangeApi;

    private static final String DATE = "2012-07-10";

    @Test
    void testResponseIsNotEmpty() {
        assertFalse(exchangeClient.getLatest(exchangeApi, DATE).equals(null));
    }

    @Test
    void testResponseIsCorrect() {
        assertTrue(exchangeClient.getLatest(exchangeApi, DATE)
                .getTimestamp()
                .equals(1341936000));
    }

    @Test
    void testGetExchangeRate() {
        assertTrue(exchangeClient.getLatest(exchangeApi, DATE)
                .getRates()
                .get("RUB") == 32.867934);
    }

}
