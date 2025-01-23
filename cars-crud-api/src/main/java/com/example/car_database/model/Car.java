package com.example.car_database.model;




import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.example.car_database.enums.Category;
import com.example.car_database.enums.Status;
import com.example.car_database.enums.converters.CategoryConverter;
import com.example.car_database.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity

//@Table(name = "Car")
@SQLDelete(sql = "UPDATE car SET status = 'Disable' WHERE id=?")
@SQLRestriction("status <> 'Disable'")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 50)
    @Column(length = 200, nullable = false)
    private String model;

    @NotBlank
    @NotNull
    @Length(min = 1, max = 50)
    @Column(length = 200, nullable = false)
    private String make;
    

   // @NotNull
   // @Length(max = 10)
    //@Pattern(regexp = "Enable|Disable")
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ENABLE;
    
   // @Length(max = 10)
    //@Pattern(regexp = "gt3|gt4")
    @Column( length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotEmpty
    @NotNull
    @Valid
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "car")
   // @JoinColumn(name = "car_id")
    private List<Driver> drivers = new ArrayList<>();

    public Car(){}

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    


}
