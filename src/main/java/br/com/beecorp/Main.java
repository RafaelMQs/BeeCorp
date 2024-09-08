package br.com.beecorp;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Install a new style for all Java Swing Components
        FlatDarculaLaf.setup();

        ExampleScreen frame = new ExampleScreen();
        frame.setVisible(true);
        frame.setTitle("BeeCorp");
        frame.setContentPane(frame.mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }
}