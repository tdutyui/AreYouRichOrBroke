package com.example.exchangerate.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
