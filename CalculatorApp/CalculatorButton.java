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
                System.out.println("checkpoint1");
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
        } else if (command.equals("+") || command.equals("-") || command.equals("/") || command.equals("*")){
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + "~" + command + "~");
            currentCalc.setDisplayText(currentCalc.getCurrentInput().replaceAll("~", ""));
        } else {
            currentCalc.setCurrentInput(currentCalc.getCurrentInput() + command);
            currentCalc.setDisplayText(currentCalc.getCurrentInput().replaceAll("~", ""));
        }
    }

    public double evaluate(String input) {
        String regex = "~"; 

        System.out.println(input);

        ArrayList<Object> calcList = new ArrayList<Object>(Arrays.asList(input.split(regex)));
        System.out.println(calcList.toString());
        for (int i=0; i<calcList.size(); i++) {
            if (!calcList.get(i).equals("+") && !calcList.get(i).equals("-") && !calcList.get(i).equals("*") && !calcList.get(i).equals("/")){
                System.out.println(calcList.get(i));
                double value = Double.parseDouble(calcList.get(i).toString());
                calcList.set(i, value);
            } 
        }

        ArrayList<Object> asdf = doOperations("*", "/", calcList);
        System.out.println(asdf.toString());
        ArrayList<Object> asdf2 =  doOperations("+", "-", asdf);
        System.out.println(asdf2.toString());

        return (double) asdf2.get(0);
        
    }

    public double solveMath(String operation, double num1, double num2){

        if (operation.equals("+")){

            System.out.println(num1 + num2);
            return num1 + num2;
        } else if (operation.equals("-")){
            return num1 - num2;
        }else if (operation.equals("*")){
            return num1 * num2;
        } else if (operation.equals("/")){
            return num1 / num2;
        } else{
            return -1.0;
        }
    }

    public ArrayList<Object> doOperations(String operator1, String operator2, ArrayList<Object> inputList){
        System.out.println(inputList.toString());
        System.out.println(operator1);
        System.out.println(operator2);


        while ((inputList.indexOf((Object) operator1) != -1) || (inputList.indexOf((Object) operator2) != -1)){
            System.out.println("checkpiont2");

            int currentOp = inputList.indexOf(operator1);
            if ((inputList.indexOf(operator2) != -1) && (currentOp > inputList.indexOf(operator2))){
                currentOp = inputList.indexOf("-");
            } else if (currentOp == -1){
                currentOp = inputList.indexOf("-");
            }

            double num1 = (double) inputList.get(currentOp - 1);
            double num2 = (double) inputList.get(currentOp + 1);
            System.out.println(num1);
            System.out.println(num2);
            System.out.println(inputList.get(currentOp));

            double resultOfOp = solveMath((String) inputList.get(currentOp), num1, num2);

            
            inputList.remove(currentOp+1);
            inputList.remove(currentOp);
            inputList.set(currentOp-1, resultOfOp);
            System.out.println(inputList.toString());
        }
        return inputList;
    }
}
