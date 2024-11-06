package br.com.beecorp.screens.sideMenu.user;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.UserModel;
import br.com.beecorp.screens.sideMenu.user.modals.UserEditModal;
import br.com.beecorp.screens.sideMenu.user.modals.UserRegisterModal;
import br.com.beecorp.screens.sideMenu.user.models.ResultTableModel;

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

public class UserScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserScreen.class.getName());

    public JPanel mainPanel;
    private JTextField userIdInput;
    private JTextField userEmailInput;
    private JButton createNewUserButton;
    private JButton deleteUserButton;
    private JButton updateUserButton;
    private JTable resultTable;
    private JButton searchUserButton;

    List<UserModel> userModels = new ArrayList<>();
    ResultTableModel resultTableModel;

    public UserScreen() {
        log.info("Open User Screen");
        userModels = getFilteredUsers(null, null);
        resultTableModel = new ResultTableModel(userModels);
        resultTable.setModel(resultTableModel);
        resultTable.setAutoCreateRowSorter(true);

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdInput.getText();
                String userEmail = userEmailInput.getText();

//                if (userId.isEmpty() && userEmail.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Os Campos: Usuario ID e Usuario E-mail não podem ser vazíos",
//                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
//                } else {
                    userModels.clear();
                    userModels.addAll(getFilteredUsers(userId, userEmail));
                    resultTableModel.fireTableDataChanged();
//                }
            }
        });

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegisterModal frame = new UserRegisterModal();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp", false);
                closeFrame(UserScreen.this);
                closeWindowAndUpdateTable(frame);
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedRow = resultTable.getSelectedRow();

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um resultado",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    UserEditModal frame = new UserEditModal(userModels.get(resultTable.getSelectedRow()));
                    frame.setContentPane(frame.mainPanel);
                    createFrame(frame, "BeeCorp", false);
                    closeFrame(UserScreen.this);
                    closeWindowAndUpdateTable(frame);
                }

            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedRow = resultTable.getSelectedRow();

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um resultado",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {

                    UserModel userModel = userModels.get(resultTable.getSelectedRow());

                    try {
                        String sql = "UPDATE bee_manager_system.tb_users" +
                                " SET" +
                                " user_active = false" +
                                " WHERE id_user = ?;";

                        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
                        ps.setInt(1, userModel.getUserId());
                        ps.executeUpdate();
                        ps.close();

                        JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        updateTable(getFilteredUsers(null, null));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Houve um erro na conexão com o banco de dados",
                                "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    List<UserModel> getFilteredUsers(String userId, String userEmail) {
        try {
            String sql;
            PreparedStatement ps;
            if ((Objects.nonNull(userId) && !userId.isEmpty())
                    && (Objects.isNull(userEmail) || userEmail.isEmpty())) {
                sql = "SELECT * FROM bee_manager_system.tb_users" +
                        " WHERE id_user = ?" +
                        " AND user_active = true";
                ps = JdbcConnection.getConnection().prepareStatement(sql);
                ps.setString(1, userId);

            } else if ((Objects.nonNull(userEmail) && !userEmail.isEmpty())
                    && (Objects.isNull(userId) || userId.isEmpty())) {
                sql = "SELECT * FROM bee_manager_system.tb_users" +
                        " WHERE ds_email = ?" +
                        " AND user_active = true";
                ps = JdbcConnection.getConnection().prepareStatement(sql);
                ps.setString(1, userEmail);
            } else if ((Objects.nonNull(userEmail) && !userEmail.isEmpty())
                    && (Objects.nonNull(userId) && !userId.isEmpty())) {
                sql = "SELECT * FROM bee_manager_system.tb_users" +
                        " WHERE id_user = ?" +
                        " AND ds_email = ?" +
                        " AND user_active = true;";
                ps = JdbcConnection.getConnection().prepareStatement(sql);
                ps.setString(1, userId);
                ps.setString(2, userEmail);
            } else {
                sql = "SELECT * FROM bee_manager_system.tb_users" +
                        " WHERE user_active = true;";
                ps = JdbcConnection.getConnection().prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();
            List<UserModel> userModels = new ArrayList<>();

            while (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setUserId(rs.getInt("id_user"));
                userModel.setUserName(rs.getString("nm_user"));
                userModel.setUserRole(rs.getString("user_role"));
                userModel.setUserEmail(rs.getString("ds_email"));
                userModel.setUserPassword(rs.getString("ps_password"));
                userModel.setUserPhone(rs.getString("nr_phone"));
                userModel.setUserZipCode(rs.getString("nr_cep"));
                userModel.setUserAddress(rs.getString("ds_address"));
                userModel.setUserCreationDate(rs.getDate("dt_creation"));
                userModel.setUserActive(rs.getBoolean("user_active"));
                userModels.add(userModel);
            }

            ps.close();
            rs.close();

            return userModels;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void updateTable(List<UserModel> userModelsData){
        userModels.clear();
        userModels.addAll(userModelsData);
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
                updateTable(getFilteredUsers(null, null));
            }
        });
    }

}
