package com.amazon.questionapp.questionbackend.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidObjectException extends  Exception{
    @Getter
    private List<String> errors ;

    public InvalidObjectException(List<String> errors){
        super(errors.toString());
        this.errors = errors;
    }
}
