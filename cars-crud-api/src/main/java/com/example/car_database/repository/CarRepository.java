package com.example.car_database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.car_database.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

    Car findByName(String name);
    List<Car> findByNameAndMake(String make ,String model);
    List<Car> findByMake(String make);
    
}
