package com.world.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name")
    private String name;

    @Column(name="CountryCode")
    private String countryCode;

    @Column(name="District")
    private String district;

    @Column(name="Population")
    private int population;



}