package br.com.beecorp.ExampleScreen;

import br.com.beecorp.jdbc.JdbcConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleScreen extends JFrame {
    public JPanel mainPanel;
    private JButton exampleJButtonActionButton;

    public ExampleScreen() throws SQLException {
        exampleJButtonActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You performed an action on the button");
            }
        });

        // SQL Example Code
        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement("SELECT * FROM bee_manager_system.tb_users;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("id_user"));
        }

        ps.close();
    }
}
