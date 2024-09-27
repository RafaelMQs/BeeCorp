package br.com.beecorp.user;

import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;
import java.awt.*;

public class UserScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private JPanel mainPanel;
    private JTextField userIdInput;
    private JTextField userEmailInput;
    private JButton createNewUserButton;
    private JButton deleteUserButton;
    private JButton updateUserButton;
    private JTable resultTable;
    private JButton searchUserButton;

    @Override
    public void createUIComponents() {
        // TODO: place custom component creation code here
        mainPanel.setBackground(Color.BLACK);
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here
    }

}
