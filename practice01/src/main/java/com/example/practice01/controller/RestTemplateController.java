package com.example.practice01.controller;

import com.example.practice01.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/client")
public class RestTemplateController {

    @Autowired
    private  RestTemplateService restTemplateService;

    @GetMapping("/hello")
    public String getHello() {
        return restTemplateService.hello();
    }

    @GetMapping("/naver")
    public String naver(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "yrW8rq53udPQ8l2RMFJB")
                .header("X-Naver-Client-Secret","OFmfaygbvH")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }
}
