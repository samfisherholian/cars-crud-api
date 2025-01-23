package com.example.car_database.dto.mapper;



import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.car_database.dto.CarDTO;
import com.example.car_database.dto.DriverDto;
import com.example.car_database.enums.Category;
import com.example.car_database.model.Car;
import com.example.car_database.model.Driver;

@Component
public class CarMapper {
    

    public CarDTO toDTO(Car car){

        if(car == null){
            return null;
        }


        List<DriverDto> drivers = car.getDrivers()
        .stream()
        .map( driver -> new DriverDto(driver.getId(), driver.getName(), driver.getNationality()))
        .collect(Collectors.toList());


        return new CarDTO(car.getId(), car.getName(), car.getModel(), car.getMake(), car.getCategory().getValue(),
        drivers);

    } 

    public Car toEntity(CarDTO carDTO){

        

        if(carDTO == null){
            return null;
        }

        Car car = new Car();

        if(carDTO.id() != null){
            car.setId(carDTO.id());
        }

        car.setName(carDTO.name());
        car.setMake(carDTO.make());
        car.setModel(carDTO.model());
        car.setCategory(converterCategoryValue(carDTO.category()));

        List<Driver> drivers = carDTO.drivers().stream().map(driverDTO -> {
            var driver = new Driver();
            //aways verify the id before insert
            if(driverDTO.id() != null){
                driver.setId(driverDTO.id());
            }
            
            driver.setName(driverDTO.name());
            driver.setNationality(driverDTO.nationality());
            driver.setCar(car);
            return driver;
        }).collect(Collectors.toList());

        car.setDrivers(drivers);

        return car;

    }

    public Category converterCategoryValue(String value){
        
        if(value == null){
            return null;

        }

        return switch(value){
            case "Gt3" -> Category.GT3;
            case "Gt4" -> Category.GT4;
            default -> throw new IllegalArgumentException("Invalid valeu " + value);
        };

    }

}
