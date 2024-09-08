package br.com.beecorp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExampleScreen extends JFrame {
    public JPanel mainPanel;
    private JButton exampleJButtonActionButton;

    public ExampleScreen() {
        exampleJButtonActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You performed an action on the button");
            }
        });
    }
}
