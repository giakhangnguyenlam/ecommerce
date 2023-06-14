package com.khangnlg.exception;

import com.khangnlg.message.ResponseMessage;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseMessage runtimeExceptionHandler(RuntimeException e){
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseMessage userExistExceptionHandler(UserExistException e){
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseMessage authenticationCredentialsNotFoundExceptionHandler(AuthenticationCredentialsNotFoundException e){
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseMessage objectNotValidExceptionHandler(ObjectNotValidException e){
        return ResponseMessage.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseMessage objectNotFoundExceptionHandler(ObjectNotFoundException e){
        return ResponseMessage.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }


}
