package com.example.developer.exception;

import lombok.Getter;
import com.example.developer.type.DMakerErrorCode;

@Getter
public class DMakerException extends RuntimeException{
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException (DMakerErrorCode errorCode){
        super(errorCode.getMessage());
        this.dMakerErrorCode=errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DMakerException (DMakerErrorCode errorCode, String detailMessage){
        super(detailMessage);
        this.dMakerErrorCode=errorCode;
        this.detailMessage= detailMessage;
    }
}
