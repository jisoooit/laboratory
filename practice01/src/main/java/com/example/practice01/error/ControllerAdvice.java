package com.example.practice01.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice()
public class ControllerAdvice {

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity numberFormatException(NumberFormatException e, HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();
        String message = e.getMessage();
        Error error = Error.builder()
                .message(message)
                .build();

        errorList.add(error);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorList(errorList)
                .requestUrl(httpServletRequest.getRequestURI())
                .resultCode("fail")
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .message("NumberFormatException")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //@ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest){
        System.out.println("여기");
        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());
            String field = list.get(list.size()-1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = Error.builder()
                    .field(field)
                    .message(message)
                    .invalidValue(invalidValue)
                    .build();

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .requestUrl(httpServletRequest.getRequestURI())
                .message("")
                .code("4000")
                .resultCode("fail")
                .errorList(errorList)
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
