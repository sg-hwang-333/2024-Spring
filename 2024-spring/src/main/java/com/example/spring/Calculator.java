package com.example.spring;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}