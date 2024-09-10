package br.com.beecorp;

import br.com.beecorp.jdbc.JdbcConnection;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Install a new style for all Java Swing Components
        FlatDarculaLaf.setup();

        ExampleScreen frame = new ExampleScreen();
        frame.setVisible(true);
        frame.setTitle("BeeCorp");
        frame.setContentPane(frame.mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFrameWindowListener(frame);
        frame.pack();
    }

    private static void setFrameWindowListener(ExampleScreen frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    JdbcConnection.closeConnection(JdbcConnection.getConnection());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
            }
        });
    }
}