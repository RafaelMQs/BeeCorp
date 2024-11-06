package br.com.beecorp.models;

import br.com.beecorp.jdbc.JdbcConnection;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                if (closeIfDispose) System.exit(0);
            }
        });
    }

    protected List<String> getEmptyFields(Object object) {
        var fields = new ArrayList<Field>();
        for (var field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(field);
        }

        List<String> emptyFields = new ArrayList<>();
        for (Field field : fields) {
            try {
                if (StringUtils.isNullOrEmpty(field.get(object).toString())) {
                    emptyFields.add(field.getName());
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        return emptyFields;
    }
}
