package com.tamziy.Library.Management.Api.Exception;

import com.tamziy.Library.Management.Api.payload.response.ApiResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.Notification;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(GenreException.class)
    public ResponseEntity<ApiResponse> handleGenreException(GenreException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ApiResponse(e.getMessage(), false));
    }
}
