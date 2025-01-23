package com.example.car_database.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity

public class Driver {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NonNull
    @NotBlank    
    @Column(length = 100, nullable = false)
    private String nationality;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Car car;


    public Driver(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", name=" + name + ", nationality=" + nationality + ", car=" + car + "]";
    }

    
    

}
