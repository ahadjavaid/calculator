package com.calculator.service;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double calculate(double firstNumber, double secondNumber, String operation) {

        switch (operation) {

            case "+":

                return firstNumber + secondNumber;

            case "-":
                return firstNumber - secondNumber;

            case "*":
                return firstNumber * secondNumber;

            case "/":

                if(secondNumber == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }

                return firstNumber / secondNumber;

            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}
