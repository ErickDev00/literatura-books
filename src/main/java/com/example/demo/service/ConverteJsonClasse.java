package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteJsonClasse implements ConverteJsonInterface {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe); //leia o json e transforme na classe indicada

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }












}
