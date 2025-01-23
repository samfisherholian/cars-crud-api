package com.example.car_database.excepition;

public class RecordNotFoundExcepiton extends RuntimeException {


    private static final long serialVersionUID = 1L;


    public RecordNotFoundExcepiton(Long id){
        super("Car not Found Err! " + id);
    }

    public RecordNotFoundExcepiton(String name){
        super("Endpoint name not found"+ name);
    }
}
