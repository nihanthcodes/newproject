package CalculatorApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;

public class CalculatorButton extends JButton implements ActionListener{
    String label;
    CalculatorFrame currentCalc;

    public CalculatorButton(String label, CalculatorFrame currentCalc){
        this.label = label;
        this.currentCalc = currentCalc;
        this.setText(label);
        currentCalc.add(this);
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                double result = evaluate(currentCalc.getCurrentInput());
                System.out.println(result);
                currentCalc.setDisplayText(String.valueOf(result));
            } catch (Exception ex) {
                System.out.println(ex);
                currentCalc.setDisplayText("Error grrr");
                currentCalc.setCurrentInput("");
            }
        } else if (command.equals("C")) {
            currentCalc.setCurrentInput("");
            currentCalc.setDisplayText("");
        } else {
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + command);
            currentCalc.setDisplayText(currentCalc.getCurrentInput());
        }
    }

    public double evaluate(String input) {
        String resultString = new String(input);

        while ((input.indexOf("*") != -1) || (input.indexOf("/") != -1)){
            // need to fix based on add & subtract logic
            int currentOp = Math.min(input.indexOf("*"), input.indexOf("/"));
        }

        while ((input.indexOf("+") != -1) || (input.indexOf("-") != -1)){
            int currentOp = input.indexOf("+");
            if ((input.indexOf("-") != -1) && (currentOp > input.indexOf("-"))){
                currentOp = input.indexOf("-");
            } else if (currentOp == -1){
                currentOp = input.indexOf("-");
            }
            

        }

        return 2.5;
        
    }
}
