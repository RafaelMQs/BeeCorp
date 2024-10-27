package br.com.beecorp.models;

import br.com.beecorp.jdbc.JdbcConnection;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public abstract class DefaultScreenAbstract extends JFrame {

    protected void createFrame(JFrame frame, String frameName, Boolean closeIfDispose) {
        frame.setVisible(true);
        frame.setTitle(frameName);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        setFrameWindowListener(frame, closeIfDispose);
    }

    protected void closeFrame(JFrame frame) {
        frame.setVisible(false);
    }

    protected void setFrameWindowListener(JFrame frame, Boolean closeIfDispose) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    JdbcConnection.closeConnection(JdbcConnection.getConnection());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
                if(closeIfDispose) System.exit(0);
            }
        });
    }
}
