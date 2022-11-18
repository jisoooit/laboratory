package com.example.developer.dto;

import lombok.*;
import com.example.developer.type.DMakerErrorCode;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {

    private DMakerErrorCode errorCode;
    private String errorMessage;


}
