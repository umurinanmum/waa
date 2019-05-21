package edu.mum.waa.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class FieldErrorDto {

    private String field;
    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
