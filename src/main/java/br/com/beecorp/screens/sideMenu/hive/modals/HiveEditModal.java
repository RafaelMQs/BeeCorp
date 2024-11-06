package br.com.beecorp.screens.sideMenu.hive.modals;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.HiveModel;
import br.com.beecorp.models.HiveUpdateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class HiveEditModal extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveEditModal.class.getName());
    public Container mainPanel;

    private JTextField hiveWeightInput;
    private JTextField hiveStatusInput;
    private JTextField hiveCodeInput;
    private JTextField hiveUserIdInput;
    private JButton UpdateButton;

    HiveModel hiveModel;

    public HiveEditModal(HiveModel hiveModel) {
        log.info("Open Hive Edit Modal");
        this.hiveModel = hiveModel;

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        this.setSize(600, 600);

        // TODO: place custom component creation code here
        hiveWeightInput.setText(hiveModel.getHiveWeight());
        hiveStatusInput.setText(hiveModel.getHiveStatus());
        hiveCodeInput.setText(hiveModel.getHiveCode());
        hiveUserIdInput.setText(hiveModel.getUserId());
    }

    @Override
    public void actionListeners() {
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HiveUpdateModel updateModel = new HiveUpdateModel();

                updateModel.setUserID(hiveUserIdInput.getText());
                updateModel.setHiveCodeInput(hiveCodeInput.getText());
                updateModel.setWeightHive(hiveWeightInput.getText());
                updateModel.setHiveStatusInput(hiveStatusInput.getText());
                updateModel.setHiveID(hiveModel.getHiveId().toString());

                List<String> emptyFields = getEmptyFields(updateModel);

                if (!emptyFields.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String sql = "UPDATE bee_manager_system.tb_hives" +
                                " SET" +
                                " id_user = ?," +
                                " cod_hive = ?," +
                                " nr_weight = ?," +
                                " st_hive = ?" +
                                " WHERE id_hive = ?;";

                        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
                        ps.setString(1, updateModel.getUserID());
                        ps.setString(2, updateModel.getHiveCodeInput());
                        ps.setString(3, updateModel.getWeightHive());
                        ps.setString(4, updateModel.getHiveStatusInput());
                        ps.setString(5, updateModel.getHiveID());
                        ps.executeUpdate();
                        ps.close();

                        JOptionPane.showMessageDialog(null, "Colmeia atualizado com sucesso",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Houve um erro na conexão com o banco de dados",
                                "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });
    }
}