package br.com.beecorp.screens.sideMenu.hive;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.HiveModel;
import br.com.beecorp.screens.sideMenu.hive.modals.HiveEditModal;
import br.com.beecorp.screens.sideMenu.hive.modals.HiveRegisterModal;
import br.com.beecorp.screens.sideMenu.hive.models.ResultTableModelHive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HiveScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveScreen.class.getName());

    public JPanel mainPanel;
    private JTextField hiveIdInput;
    private JButton updateHiveButton;
    private JButton createNewHiveButton;
    private JTable resultTable;
    private JButton searchHiveButton;

    List<HiveModel> hiveModels = new ArrayList<>();
    ResultTableModelHive resultTableModel;

    public HiveScreen() {
        log.info("Open Hive Screen");
        hiveModels = getFilteredHives(null);
        resultTableModel = new ResultTableModelHive(hiveModels);
        resultTable.setModel(resultTableModel);
        resultTable.setAutoCreateRowSorter(true);

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

        searchHiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hiveId = hiveIdInput.getText();

//                if (hiveId.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "O Campo: Hive ID não pode ser vazio",
//                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
//                } else {
                hiveModels.clear();
                hiveModels.addAll(getFilteredHives(hiveId));
                resultTableModel.fireTableDataChanged();
//                }

            }
        });

        createNewHiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HiveRegisterModal frame = new HiveRegisterModal();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp", false);
                closeFrame(HiveScreen.this);
                closeWindowAndUpdateTable(frame);

            }
        });

        updateHiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedRow = resultTable.getSelectedRow();

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um resultado",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    HiveEditModal frame = new HiveEditModal(hiveModels.get(resultTable.getSelectedRow()));
                    frame.setContentPane(frame.mainPanel);
                    createFrame(frame, "BeeCorp", false);
                    closeFrame(HiveScreen.this);
                    closeWindowAndUpdateTable(frame);
                }

            }
        });
    }

    List<HiveModel> getFilteredHives(String hiveId) {
        try {
            String sql;
            PreparedStatement ps;
            if (Objects.nonNull(hiveId) && !hiveId.isEmpty()) {
                sql = "SELECT * FROM bee_manager_system.tb_hives" +
                        " WHERE id_hive = ?";
                ps = JdbcConnection.getConnection().prepareStatement(sql);
                ps.setString(1, hiveId);

            } else {
                sql = "SELECT * FROM bee_manager_system.tb_hives";
                ps = JdbcConnection.getConnection().prepareStatement(sql);

            }

            ResultSet rs = ps.executeQuery();
            List<HiveModel> hiveModels = new ArrayList<>();

            while (rs.next()) {
                HiveModel hiveModel = new HiveModel();
                hiveModel.setHiveId(rs.getInt("id_hive"));
                hiveModel.setHiveCode(rs.getString("cod_hive"));
                hiveModel.setHiveStatus(rs.getString("st_hive"));
                hiveModel.setHiveWeight(rs.getString("nr_weight"));
                hiveModel.setUserId(rs.getString("id_user"));
                hiveModel.setHiveCreationDate(rs.getDate("dt_creation"));
                hiveModels.add(hiveModel);
            }

            ps.close();
            rs.close();

            return hiveModels;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void updateTable(List<HiveModel> hiveModelsData) {
        hiveModels.clear();
        hiveModels.addAll(hiveModelsData);
        resultTableModel.fireTableDataChanged();
    }

    void closeWindowAndUpdateTable(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    JdbcConnection.closeConnection(JdbcConnection.getConnection());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
                updateTable(getFilteredHives(null));
            }
        });
    }
}





