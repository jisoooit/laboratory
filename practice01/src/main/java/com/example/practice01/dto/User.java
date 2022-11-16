package com.example.practice01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL) //reponse내릴때 null값 포함시키지 않음
public class User {
    //@JsonProperty("user_id")
    private int userId;

    @ApiModelProperty(value = "사용자 이름", example = "steve")
    private String name;

    @ApiModelProperty(value = "사용자 이메일", example = "y@naver.com")

    private String email;

    @Builder
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
