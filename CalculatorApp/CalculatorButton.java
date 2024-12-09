package CalculatorApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
                currentCalc.setDisplayText(String.valueOf(result));
            } catch (Exception ex) {
                currentCalc.setDisplayText("Error grrr");
                currentCalc.setCurrentInput("");
            }
        } else if (command.equals("C")) {
            currentCalc.setCurrentInput("");
            currentCalc.setDisplayText("");
        } else if (command.equals("+") || command.equals("-") || command.equals("/") || command.equals("*")){
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + "~" + command + "~");
            currentCalc.setDisplayText(currentCalc.getCurrentInput().replaceAll("~", ""));
        } else if (command.equals("sqrt")){
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + "~" + command);
            currentCalc.setDisplayText(currentCalc.getCurrentInput().replaceAll("~", ""));
        } else {
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + command);
            currentCalc.setDisplayText(currentCalc.getCurrentInput().replaceAll("~", ""));
        }
    }

    public double evaluate(String input) {
        String regex = "~"; 

        ArrayList<Object> calcList = new ArrayList<Object>(Arrays.asList(input.split(regex)));
        for (int i=0; i<calcList.size(); i++) {
            if (!calcList.get(i).equals("+") && !calcList.get(i).equals("-") && !calcList.get(i).equals("*") && !calcList.get(i).equals("/")){
                double value = Double.parseDouble(calcList.get(i).toString());
                calcList.set(i, value);
            } 
        }

        ArrayList<Object> asdf = doOperations("*", "/", calcList);
        ArrayList<Object> asdf2 =  doOperations("+", "-", asdf);

        return (double) asdf2.get(0);
        
    }

    public double solveMath(String operation, double num1, double num2){
        if (operation.equals("+")){
            return num1 + num2;
        } else if (operation.equals("-")){
            return num1 - num2;
        }else if (operation.equals("*")){
            return num1 * num2;
        } else if (operation.equals("/")){
            System.out.println(num1/num2);
            return num1 / num2;
        } else{
            return -1.0;
        }
    }

    public ArrayList<Object> doOperations(String operator1, String operator2, ArrayList<Object> inputList){
        while ((inputList.indexOf((Object) operator1) != -1) || (inputList.indexOf((Object) operator2) != -1)){
            int currentOp = inputList.indexOf(operator1);
            if ((inputList.indexOf(operator2) != -1) && (currentOp > inputList.indexOf(operator2))){
                currentOp = inputList.indexOf(operator2);
            } else if (currentOp == -1){
                currentOp = inputList.indexOf(operator2);
            }

            double num1 = (double) inputList.get(currentOp - 1);
            double num2 = (double) inputList.get(currentOp + 1);

            double resultOfOp = solveMath((String) inputList.get(currentOp), num1, num2);

            inputList.remove(currentOp+1);
            inputList.remove(currentOp);
            inputList.set(currentOp-1, resultOfOp);
        }
        return inputList;
    }
}
