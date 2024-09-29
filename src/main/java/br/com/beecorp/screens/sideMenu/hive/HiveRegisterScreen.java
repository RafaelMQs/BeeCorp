package br.com.beecorp.screens.sideMenu.hive;

import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;
import java.util.logging.Logger;

public class HiveRegisterScreen extends JPanel implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveRegisterScreen.class.getName());

    private JTextField hiveStatusInput;
    private JPanel mainPanel;
    private JTextField textField3;
    private JTextField hiveIdInput;
    private JTextField hiveCodeInput;
    private JTextField textField6;
    private JTextField textField7;
    private JFormattedTextField formattedTextField1;

    public HiveRegisterScreen() {
        log.info("Open Hive Register Screen");

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
    }

}
