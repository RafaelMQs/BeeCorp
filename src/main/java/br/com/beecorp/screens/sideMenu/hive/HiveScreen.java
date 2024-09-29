package br.com.beecorp.screens.sideMenu.hive;

import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;
import java.util.logging.Logger;

public class HiveScreen extends JPanel implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveScreen.class.getName());

    public JPanel mainPanel;
    private JTextField hiveIdInput;
    private JButton updateHiveButton;
    private JButton deleteHiveButton;
    private JButton createNewHiveButton;
    private JTable resultTable;
    private JButton searchHiveButton;

    public HiveScreen() {
        log.info("Open Hive Screen");

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
