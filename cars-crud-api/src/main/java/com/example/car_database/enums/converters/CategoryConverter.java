package com.example.car_database.enums.converters;

import com.example.car_database.enums.Category;

import java.util.stream.*;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        // TODO Auto-generated method stub
        if(category == null){
            return null;
        }

        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        // TODO Auto-generated method stub
        if(value == null){
            return null;
        }

        return Stream.of(Category.values())
        .filter(c -> c.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
    


}
