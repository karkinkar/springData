package com.example.demo.adapters.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String City;
    private String Street;
    private Integer HouseNumber;
    private String Country;
}
