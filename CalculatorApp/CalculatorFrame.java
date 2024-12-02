package CalculatorApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
class CalculatorFrame extends JFrame {
    JTextField display;
    String currentInput = "";

    public CalculatorFrame() {
        setTitle("Calculator");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setVisible(true);

        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 50));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); 
        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            ".", "0", "=", "/"
        };

        for (String label : buttons) {
            CalculatorButton button = new CalculatorButton(label, this);
            button.addActionListener();
            buttonPanel.add(button);
        }

        JButton clearButton = new JButton("Clear");
        
        add(buttonPanel, BorderLayout.CENTER);
        pack();  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                double result = calculator.evaluate(currentInput);
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);  
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


    public String getCurrentInput() {
        return currentInput;
    }
}

