package CalculatorApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
class CalculatorFrame extends JFrame {
    JTextField display;
    String currentInput = "";

    public CalculatorFrame() {
        super();
        super.setTitle("Calculator");
        super.setLayout(new BorderLayout(10, 10));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(450, 500);
    
        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 50));
        super.add(display, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); 
        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "C", "0", "=", "/"
        };
    
        for (String label : buttons) {
            CalculatorButton button = new CalculatorButton(label, this);
            buttonPanel.add(button);
        }
        
        super.add(buttonPanel, BorderLayout.CENTER);
        super.pack();  
        super.setLocationRelativeTo(null);
        super.setVisible(true); // Move this line to the end
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String newInput) {
        currentInput = newInput;
    }

    public void setDisplayText(String newText){
        display.setText(newText);
    }
}

