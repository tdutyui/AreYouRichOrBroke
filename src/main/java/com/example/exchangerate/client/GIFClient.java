package com.example.exchangerate.client;

import com.example.exchangerate.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy", url = "${giphy.url}")
public interface GIFClient {

    @GetMapping("/search")
    public Gif getGifs(@RequestParam(name = "api_key") String api, @RequestParam(name = "q") String query);
}
