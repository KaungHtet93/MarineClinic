package com.apsn.MarineClinic.dto.Exceptions;

public class NameDuplicateException  extends RuntimeException{
    public NameDuplicateException(String message) {
        super(message);
    }
}
