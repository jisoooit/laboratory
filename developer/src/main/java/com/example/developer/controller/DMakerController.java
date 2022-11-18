package com.example.developer.controller;

import com.example.developer.dto.CreateDeveloper;
import com.example.developer.dto.DeveloperDetailDto;
import com.example.developer.dto.DeveloperDto;
import com.example.developer.dto.EditDeveloper;
import com.example.developer.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("Get /developers Http/1.1");

        return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developers/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable String memberId
    ) {
        log.info("Get /developers Http/1.1");

        return dMakerService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request
            ) {
        log.info("request: {}", request);

        return  dMakerService.createDeveloper(request);

    }

    @PutMapping("/developers/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request
    ) {
        log.info("Put /developers Http/1.1");

        return dMakerService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developers/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable String memberId
    ) {
        return dMakerService.deleteDeveloper(memberId);
    }
}
