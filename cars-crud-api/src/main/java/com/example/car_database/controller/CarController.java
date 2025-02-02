package com.example.car_database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.car_database.dto.CarDTO;
import com.example.car_database.dto.CarPageDto;
import com.example.car_database.model.Car;
import com.example.car_database.repository.CarRepository;
import com.example.car_database.service.CarService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/cars")
public class CarController {

    


    //@Autowired
    private  final CarRepository carRepository;

    private final CarService carService;

    public CarController(CarRepository carRepository, CarService carService){
        this.carRepository = carRepository;
        this.carService = carService;
    }


    @GetMapping
    public CarPageDto carList( @RequestParam(defaultValue = "0") @PositiveOrZero int page,@RequestParam(defaultValue = "10") @Positive @Max(100) int size){

        //List<Car> car = carRepository.findAll();

        return carService.carList(page, size);

    }

/* 
 *     @GetMapping
    public List<CarDTO> carList(){

        //List<Car> car = carRepository.findAll();

        return carService.carList();

    }
 * 
*/


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarDTO addCar(@RequestBody @Valid @NotNull CarDTO car){
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    public CarDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CarDTO car){
        return carService.update(id, car);
    }

    @GetMapping("/{id}")
    public CarDTO findByiD(@PathVariable @NotNull @Positive Long id){

         return carService.findByiD(id);
       
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable @NotNull @Positive Long id){


        carService.delete(id);
       

    }

    @GetMapping("/findbyname")
    public Car showByName(@RequestParam(required = true) String name ){

       
        return carService.findCarByName(name);
       

    }

}
