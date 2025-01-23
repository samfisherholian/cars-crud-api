package com.example.car_database.enums;

public enum Category {
    
    GT3("Gt3"), GT4("Gt4");

    private String value;

    private Category(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.value;
    }

}
