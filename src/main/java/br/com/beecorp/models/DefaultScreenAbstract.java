package br.com.beecorp.models;

import br.com.beecorp.jdbc.JdbcConnection;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public abstract class DefaultScreenAbstract extends JFrame {

    protected void createFrame(JFrame frame, String frameName){
        frame.setVisible(true);
        frame.setTitle(frameName);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        setFrameWindowListener(frame);
    }

    protected void closeFrame(JFrame frame) {
        frame.setVisible(false);
    }

    protected void setFrameWindowListener(JFrame frame) {
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
