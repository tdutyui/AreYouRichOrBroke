package com.example.exchangerate.client;

import com.example.exchangerate.model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchange", url = "${exchange.url}")
public interface ExchangeClient {

    @GetMapping("/historical/{date}.json")
    public Rate getLatest(@RequestParam(name = "app_id") String appid, @PathVariable String date);
}
