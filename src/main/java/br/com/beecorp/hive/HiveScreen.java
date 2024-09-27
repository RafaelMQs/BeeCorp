package br.com.beecorp.hive;

import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;

import javax.swing.*;

public class HiveScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    public JPanel mainPanel;
    private JTextField hiveIdInput;
    private JButton updateHiveButton;
    private JButton deleteHiveButton;
    private JButton createNewHiveButton;
    private JTable resultTable;
    private JButton searchHiveButton;

    @Override
    public void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here
    }


}
