package com.example.developer.service;

import com.example.developer.dto.CreateDeveloper;
import com.example.developer.dto.DeveloperDetailDto;
import com.example.developer.dto.DeveloperDto;
import com.example.developer.dto.EditDeveloper;
import com.example.developer.entity.Developer;
import com.example.developer.entity.RetiredDeveloper;
import com.example.developer.exception.DMakerException;
import com.example.developer.repository.DeveloperRepository;
import com.example.developer.repository.RetiredDeveloperRepository;
import com.example.developer.type.DeveloperLevel;
import com.example.developer.code.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.developer.type.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    public  List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream()
                .map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYear())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {

        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYear()
        );

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATE_MEMBER_ID);
                }));
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(()-> new DMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(request);
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(()-> new DMakerException(NO_DEVELOPER)
        );

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYear());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(EditDeveloper.Request request) {
        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYear()
        );
    }

    private static void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYear) {
        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYear < 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGNIOR
                && experienceYear < 4 || experienceYear > 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if(developerLevel == DeveloperLevel.JUNIOR
                && experienceYear > 4){
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // Employed -> retired
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(()->new DMakerException(NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        // save into retiredDeveloper
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}

