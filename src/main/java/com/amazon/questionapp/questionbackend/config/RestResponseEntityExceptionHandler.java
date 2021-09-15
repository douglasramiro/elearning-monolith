package com.amazon.questionapp.questionbackend.config;

import com.amazon.questionapp.questionbackend.dto.ExceptionMessageDTO;
import com.amazon.questionapp.questionbackend.dto.ExceptionMessagesDTO;
import com.amazon.questionapp.questionbackend.exceptions.BusinessException;
import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import com.amazon.questionapp.questionbackend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidObjectException.class)
    public ResponseEntity<Object> handleInvalidObjectException(InvalidObjectException ex) {
        ExceptionMessagesDTO messages = new ExceptionMessagesDTO();
        ex.getErrors().forEach(msg->{
            messages.addExceptionMessage(new ExceptionMessageDTO(msg));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        ExceptionMessagesDTO messages = new ExceptionMessagesDTO();
        messages.addExceptionMessage(new ExceptionMessageDTO(ex.getMessage()));
        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex) {
        ExceptionMessagesDTO messages = new ExceptionMessagesDTO();
        messages.addExceptionMessage(new ExceptionMessageDTO(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages);
    }




}
