package com.example.car_database.enums.converters;

import com.example.car_database.enums.Category;
import com.example.car_database.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.*;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        // TODO Auto-generated method stub
        if(status == null){
            return null;
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        // TODO Auto-generated method stub
        if(value == null){
            return null;
        }

        return Stream.of(Status.values())
        .filter(c -> c.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
    
}
