package com.example.exchangerate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Rate {

    String disclaimer;
    String license;
    Integer timestamp;
    String base;
    HashMap<String, Double> rates;
}
