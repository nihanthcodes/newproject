package CalculatorApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;

public class CalculatorButton extends JButton implements ActionListener{
    String label;
    CalculatorFrame currentCalc;

    public CalculatorButton(String label, CalculatorFrame currentCalc){
        this.label = label;
        this.currentCalc = currentCalc;
        JButton button = new JButton(label);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                double result = evaluate(currentCalc.getCurrentInput());
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result); ; 
            } catch (Exception ex) {
                display.setText("Error");
                currentInput = "";
            }
        } else if (command.equals("C")) {
            currentInput = "";
            display.setText("");
        } else {
            currentInput += command;
            display.setText(currentInput);
        }
    }

    public double evaluate(String input) {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            return (double) engine.eval(input);
        } catch (Exception e) {
            throw new RuntimeException("Invalid expression");
        }
    }
}
