package com.calculator.controller;

import com.calculator.service.CalculatorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CalculatorController {

    @FXML
    private TextField display;

    private final CalculatorService calculatorService;

    private boolean startNewNumber = true;
    private boolean calculationFinished = false;
    private double firstNumber;
    private String operator;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @FXML
    public void handleNumber(ActionEvent event) {

        Button button = (Button) event.getSource();

        String number = button.getText();

        if(startNewNumber || calculationFinished) {
            display.setText(number);
            startNewNumber = false;
            calculationFinished = false;
        } else {
            display.appendText(number);
        }
    }

    @FXML
    public void handleOperator(ActionEvent event ) {

        Button button = (Button) event.getSource();
        String selectedOperator = button.getText();
        firstNumber = Double.parseDouble(display.getText());
        operator = selectedOperator;
        startNewNumber = true;
    }
 
    @FXML
    public void handleEquals(ActionEvent event) {

        try {

            double secondNumber = Double.parseDouble(display.getText());

            double result = calculatorService.calculate(firstNumber, secondNumber, operator);

            display.setText(String.valueOf(result));

            calculationFinished = true;

        } catch (ArithmeticException e) {
            display.setText(e.getMessage());
        }
    }

    @FXML
    public void handleClear(ActionEvent event) {

        display.setText("0");
        firstNumber = 0;
        operator = null;

        startNewNumber = true;
    }

    @FXML
    public void handleDecimal(ActionEvent event) {

        if (startNewNumber || calculationFinished) {
            display.setText("0.");
            startNewNumber = false;
            calculationFinished = false;
        } else if(!display.getText().contains(".")) {
            display.appendText(".");
        }
    }
}


