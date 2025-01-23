package com.example.car_database.dto;

import java.util.List;

import com.example.car_database.model.Driver;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarDTO ( 
    Long id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String name,
    @NotNull @NotBlank @Length(min = 1, max = 50) String model,
    @NotNull @NotBlank @Length(min = 1, max = 50) String make,
    @NotNull String category,
    @NotNull @NotEmpty @Valid List<DriverDto> drivers 
    ) {
    
}
