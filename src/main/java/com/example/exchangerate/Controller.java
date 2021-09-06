package com.example.exchangerate;

import com.example.exchangerate.client.ExchangeClient;
import com.example.exchangerate.client.GIFClient;
import com.example.exchangerate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
public class Controller {

    @Value("${exchange.api}")
    private String exchangeApi;
    @Value("${giphy.api}")
    private String giphyApi;
    @Value("${search.bad}")
    private String broke;
    @Value("${search.good}")
    private String rich;
    @Value("${before.id}")
    private String before;
    @Value("${after.id}")
    private String after;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd").withZone(ZoneId.of("UTC"));
    String today = formatter.format(Instant.now());
    String yesterday = formatter.format(Instant.now().minus(1, ChronoUnit.DAYS));

    @Autowired
    private ExchangeClient client;
    @Autowired
    private GIFClient giphy;

    @GetMapping(value = "/exchange", produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] getRates(@RequestParam String code) throws IOException {
        return ratesForDay(today, code) >= ratesForDay(yesterday, code) ?
                gifToBytes(getLink(rich)) :
                gifToBytes(getLink(broke));
    }

    public Double ratesForDay(String date, String code) {
        Rate rate = client.getLatest(exchangeApi, date);
        HashMap<String, Double> currencies = rate.getRates();
        return currencies.get(code.toUpperCase());
    }

    public String getLink(String search) {
        Random random = new Random();
        Gif gif = giphy.getGifs(giphyApi, search);
        List<Datum> data = gif.getData();
        String id = data.get(random.nextInt(data.size())).getId();
        return before + id + after;
    }

    public byte[] gifToBytes(String link) throws IOException {
        InputStream input = new URL(link).openStream();
        return input.readAllBytes();
    }
}
