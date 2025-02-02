package com.example.car_database.service;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.car_database.dto.CarDTO;
import com.example.car_database.dto.CarPageDto;
import com.example.car_database.dto.mapper.CarMapper;
import com.example.car_database.excepition.RecordNotFoundExcepiton;
import com.example.car_database.model.Car;
import com.example.car_database.repository.CarRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CarService {


    
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper){
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    public CarPageDto carList(@PositiveOrZero int page, @Positive @Max(100) int size){

        Page<Car> pageCars = carRepository.findAll(PageRequest.of(page,size));
        List<CarDTO> cars = pageCars.get().map(carMapper::toDTO).collect(Collectors.toList());

        return new CarPageDto(cars, pageCars.getTotalElements(), pageCars.getTotalPages());
    }

  

/*
 *  public List<CarDTO> carList(){

        return carRepository.findAll().stream()
        .map(carMapper::toDTO)
        .collect(Collectors.toList());

    }
 * 
 */
   

    public CarDTO findByiD( @NotNull @Positive Long id){

        return carRepository.findById(id).map(carMapper::toDTO).orElseThrow(() -> new RecordNotFoundExcepiton(id));
    
    }

    public CarDTO addCar(@Valid @NotNull CarDTO car){
        return carMapper.toDTO(carRepository.save(carMapper.toEntity(car)));
    }


    public CarDTO update( @NotNull @Positive Long id, @Valid @NotNull CarDTO car){
        return carRepository.findById(id).map(
            recordFound -> {

                Car car_ = carMapper.toEntity(car);

                recordFound.setMake(car.make());
                recordFound.setModel(car.model());
                recordFound.setName(car.name());
                recordFound.setCategory(carMapper.converterCategoryValue(car.category()));

                recordFound.getDrivers().clear();
                car_.getDrivers().forEach( drivers -> recordFound.getDrivers().add(drivers));

                return carMapper.toDTO(carRepository.save(recordFound));
            }
        ).orElseThrow(() -> new RecordNotFoundExcepiton(id));
    }


    public void delete (@NotNull @Positive Long id){


        carRepository.delete(carRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundExcepiton(id)));

    }


    public Car findCarByName(@RequestParam(required = true) String name){

            Car car = carRepository.findByName(name);
        
            if(car == null){

                throw new RecordNotFoundExcepiton(name);
            }            

           return car;

    }
    
}
