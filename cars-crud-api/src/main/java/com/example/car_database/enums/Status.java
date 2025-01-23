package com.example.car_database.enums;

public enum Status {
    ENABLE("Enable"), DISABLE("Disable");

    private String value;

    private Status(String value){
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
