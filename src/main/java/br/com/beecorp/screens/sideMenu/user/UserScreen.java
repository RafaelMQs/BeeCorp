package br.com.beecorp.screens.sideMenu.user;

import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;
import java.util.logging.Logger;

public class UserScreen extends JPanel implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserScreen.class.getName());

    public JPanel mainPanel;
    private JTextField userIdInput;
    private JTextField userEmailInput;
    private JButton createNewUserButton;
    private JButton deleteUserButton;
    private JButton updateUserButton;
    private JTable resultTable;
    private JButton searchUserButton;

    public UserScreen() {
        log.info("Open User Screen");

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
    }

}
