package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JLabel display;
    private Double leftOperand;
    private Double rightOperand;
    private String operation;

    public static void main(String[] args) {
        new Calculator();
    }

    public Calculator(){
        setTitle("Calculator");
        setBounds(100,100,300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



       final ActionListener numberButton =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                final String text = source.getText();
                String displayText = display.getText();
                if ("0".equals(displayText)){
                    displayText = "";
                } else {
                    displayText += text;
                    display.setText(displayText);
                }
            }
        };
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                final String text = source.getText();
                leftOperand = Double.parseDouble(display.getText());
                operation = text;
                if ("=".equals(text)){
                    if (leftOperand != null && rightOperand != null){
                        switch (operation){
                            case "+":
                                display.setText(String.valueOf(leftOperand + rightOperand));
                                break;
                            case "-":
                                display.setText(String.valueOf(leftOperand - rightOperand));
                                break;
                            case "/":
                                display.setText(String.valueOf(leftOperand / rightOperand));
                                break;
                            case "*":
                                display.setText(String.valueOf(leftOperand * rightOperand));
                                break;
                        }
                        leftOperand = Double.parseDouble(display.getText());
                        operation = null;
                        rightOperand = null;
                    }
                }
                leftOperand = Double.parseDouble(display.getText());


            }
        };
        final JPanel numberPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3, 6, 6);
        numberPanel.setLayout(gridLayout);
        for (int i = 0; i < 10; i++){
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(numberButton);
            numberPanel.add(button);

        }
        numberPanel.add(new JButton("."));
        numberPanel.add(new JButton("=/-"));


        final JPanel buttonPanel = new JPanel();
        final GridLayout buttonLayout = new GridLayout(1,6,6,6);
        buttonPanel.setLayout(buttonLayout);

        for (char c: "+-*/C=".toCharArray()){
            JButton button = new JButton(String.valueOf(c));
            buttonPanel.add(button);
            button.addActionListener(actionListener);
        }

        display = new JLabel("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 17));

        add(display, BorderLayout.NORTH);
        add(numberPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);



        setVisible(true);
    }
}
