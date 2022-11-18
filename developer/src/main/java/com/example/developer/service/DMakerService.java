package com.example.developer.service;

import com.example.developer.dto.CreateDeveloper;
import com.example.developer.entity.Developer;
import com.example.developer.repository.DeveloperRepository;
import com.example.developer.type.DeveloperLevel;
import com.example.developer.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper(CreateDeveloper.Request request){
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("elsa")
                .age(10)
                .build();

        developerRepository.save(developer);
    }
}

