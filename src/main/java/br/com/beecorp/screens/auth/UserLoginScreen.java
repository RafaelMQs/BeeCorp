package br.com.beecorp.screens.auth;

import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.screens.sideMenu.SideMenuScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class UserLoginScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserLoginScreen.class.getName());

    public JPanel mainPanel;
    private JTextField userNameInput;
    private JPasswordField userPasswordInput;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel teste;

    public UserLoginScreen() {
        log.info("Open User Login Screen");

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        this.setSize(600, 600);
        // TODO: place custom component creation code here
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SideMenuScreen frame = new SideMenuScreen();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp");
                closeFrame(UserLoginScreen.this);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegisterScreen frame = new UserRegisterScreen();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp");
                closeFrame(UserLoginScreen.this);
            }
        });
    }
}
