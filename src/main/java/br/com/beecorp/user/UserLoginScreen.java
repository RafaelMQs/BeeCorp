package br.com.beecorp.user;

import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    public JPanel mainPanel;
    private JTextField userNameInput;
    private JPasswordField userPasswordInput;
    private JButton loginButton;
    private JButton registerButton;

    public UserLoginScreen() {
        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegisterScreen frame = new UserRegisterScreen();
                frame.setVisible(true);
                frame.setTitle("BeeCorp");
                frame.setContentPane(frame.mainPanel);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                setFrameWindowListener(frame);
                closeFrame(UserLoginScreen.this);
            }
        });
    }
}
