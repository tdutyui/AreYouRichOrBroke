package com.example.exchangerate;

import com.example.exchangerate.client.GIFClient;
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
public class GIFClientTests {

    @Autowired
    private GIFClient gifClient;

    @Value("${giphy.api}")
    private String giphyApi;

    @Test
    void testResponseIsNotEmpty() {
        assertFalse(gifClient.getGifs(giphyApi, "test").equals(null));
    }

    @Test
    void testResponseIsCorrect() {
        assertTrue(gifClient.getGifs(giphyApi, "test")
                .getData()
                .size() == 50);
    }

    @Test
    void testGetCorrectGifId() {
        assertTrue(gifClient.getGifs(giphyApi, "test")
                .getData()
                .get(0)
                .getId()
                .equals("gw3IWyGkC0rsazTi"));
    }

}
