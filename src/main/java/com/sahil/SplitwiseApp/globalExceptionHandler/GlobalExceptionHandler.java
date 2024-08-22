package com.sahil.SplitwiseApp.globalExceptionHandler;

import com.sahil.SplitwiseApp.DTO.exceptionDTOs.*;
import com.sahil.SplitwiseApp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResourceNotFoundExceptionDTO.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<?> emptyInputException(EmptyInputException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(EmptyInputExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<?> invalidAmountException(InvalidAmountException exception){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EmptyInputExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateUserException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DuplicateUserExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NullFieldException.class)
    public ResponseEntity<?> handleNullFieldException(NullFieldException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(NullFieldExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBool(HttpMessageNotReadableException httpMessageNotReadableException){
        InValidIsSettledException exception = new InValidIsSettledException("IsSettled can take only true or false");
        return handleIsSettled(exception);
    }

    @ExceptionHandler(InValidIsSettledException.class)
    public ResponseEntity<?> handleIsSettled(InValidIsSettledException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(InValidIsSettledExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(InvalidAmountBreakDownException.class)
    public ResponseEntity<?> handleInvalidAmountBreakdown(InvalidAmountBreakDownException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(InvalidAmountBreakDownExceptionDTO.builder()
                        .msg(exception.getMessage())
                        .build());
    }

}