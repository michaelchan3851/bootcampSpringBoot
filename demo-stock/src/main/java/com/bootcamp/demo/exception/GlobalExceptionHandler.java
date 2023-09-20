package com.bootcamp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.infra.ApiResponse;
import com.bootcamp.demo.infra.Code;

import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = SYMExpection.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ApiResponse<Void> finnhubExceptionHandler(SYMExpection e) {
    return ApiResponse.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> runtimeExceptionHandler(RuntimeException e) {
    return ApiResponse.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage())
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> exceptionHandler(Exception e) {
    return ApiResponse.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage())
        .data(null) //
        .build();
  }

  private static Code getRespCode(Exception e) {
    if (e instanceof IllegalArgumentException) {
      return Code.IAE_EXCEPTION;
    }else if(e instanceof EntityNotFoundException){
      return Code.ENTITY_NOTFOUND;
    }
    // ...
    return null;
  }
}