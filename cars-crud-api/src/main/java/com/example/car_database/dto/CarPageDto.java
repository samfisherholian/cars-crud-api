package com.example.car_database.dto;

import java.util.List;

import com.example.car_database.model.Car;

public record CarPageDto(List<CarDTO> cars, long totalElements, int totalPages) {
    
}
