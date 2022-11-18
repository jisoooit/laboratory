package com.example.developer.controller;

import com.example.developer.dto.CreateDeveloper;
import com.example.developer.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        log.info("Get /developers Http/1.1");

        return Arrays.asList("snow", "grace", "elsa");
    }

    @PostMapping("/create-developers")
    public List<String> createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request
            ) {
        log.info("request: {}", request);

        dMakerService.createDeveloper(request);

        return List.of("elsa");
    }

}
