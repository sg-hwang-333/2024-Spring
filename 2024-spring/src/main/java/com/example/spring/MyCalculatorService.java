package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyCalculatorService {

    // 해당 필드(calculator)에 의존성 주입(네가 대신 Application 에서 Calculator 이름의 Bean 좀 찾아서 넣어줘라)이 진행될 수 있도록 Autowired 어노테이션을 적용

    // -------
    // 필드 위에 Autowired 어노테이션 붙이기
//    @Autowired
//    private Calculator calculator;

    // -------
    // 상수 필드의 경우 생성자에서 대입은 허용하므로 final로 설정
    private final Calculator calculator;
    // 생정자 위에 Autowired 어노테이션 붙이기
    // 의존성 주입을 통해 생성자로 Calculator 타입의 Bean 객체가 주입(=전달)됨
    @Autowired
    public MyCalculatorService(Calculator calculator) {
        System.out.println("from constructor");
        System.out.println(calculator);
        this.calculator = calculator;
    }
    // -------
//    private Calculator calculator;
//    // 메서드 위에 Autowired 어노테이션 붙이기
//    @Autowired
//    public void setCalculator(Calculator calculator) {
//        System.out.println("from setCalculator");
//        System.out.println(calculator);
//        this.calculator = calculator;
//    }

    public int calcAdd(int a, int b) {
        return calculator.add(a, b);
    }
}
