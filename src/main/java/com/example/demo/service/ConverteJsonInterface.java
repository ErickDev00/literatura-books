package com.example.demo.service;

public interface ConverteJsonInterface {
    <T> T obterDados(String json, Class<T> classe);
}
