package com.example.demo.adapters.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String City;
    private String Street;
    private Integer HouseNumber;
    private String Country;
}
