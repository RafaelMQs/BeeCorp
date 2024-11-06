package br.com.beecorp.screens.sideMenu.hive.modals;

import br.com.beecorp.GlobalVariables;
import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.HiveRegisterModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;

public class HiveRegisterModal extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveRegisterModal.class.getName());

    private JTextField hiveStatusInput;
    public JPanel mainPanel;
    private JTextField hiveCodeInput;
    private JTextField hiveWeightInput;
    private JButton registrarButton;

    public HiveRegisterModal() {
        log.info("Open Hive Register Modal");

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        this.setSize(400, 400);
        // TODO: place custom component creation code here
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!hiveWeightInput.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "O Peso deve conter somente numeros", "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    HiveRegisterModel registerModel = new HiveRegisterModel();
                    registerModel.setHiveStatus(hiveStatusInput.getText());
                    registerModel.setHiveCode(hiveCodeInput.getText());
                    registerModel.setWeightHive(Double.valueOf(hiveWeightInput.getText()));
                    registerModel.setUserId(GlobalVariables.userLogin.getUserId().toString());

                    List<String> emptyFields = getEmptyFields(registerModel);

                    if (!emptyFields.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos", "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                    }
                    try {
                        String sql = "INSERT INTO bee_manager_system.tb_hives (id_user, cod_hive, nr_weight, st_hive, dt_creation)" + "VALUES(?,?,?,?,?);";

                        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
                        ps.setString(1, registerModel.getUserId());
                        ps.setString(2, registerModel.getHiveCode());
                        ps.setDouble(3, registerModel.getHiveWeight());
                        ps.setString(4, registerModel.getHiveStatus());
                        ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                        ps.executeUpdate();
                        ps.close();

                        JOptionPane.showMessageDialog(null, "Colmeia cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null, "ID já cadastrado", "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Houve um erro na conexão com o banco de dados", "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

    }
}





