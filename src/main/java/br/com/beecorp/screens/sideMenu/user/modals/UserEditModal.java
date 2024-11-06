package br.com.beecorp.screens.sideMenu.user.modals;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.UserModel;
import br.com.beecorp.models.UserUpdateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserEditModal extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserRegisterModal.class.getName());

    public JPanel mainPanel;
    private JTextField userNameInput;
    private JTextField userCep;
    private JTextField userPhone;
    private JTextField userAddress;
    private JButton updateButton;

    UserModel userModel;

    public UserEditModal(UserModel userModel) {
        log.info("Open User Register Screen");
        this.userModel = userModel;

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        this.setSize(600, 600);

        // TODO: place custom component creation code here
        userNameInput.setText(userModel.getUserName());
        userCep.setText(userModel.getUserZipCode());
        userPhone.setText(userModel.getUserPhone());
        userAddress.setText(userModel.getUserAddress());
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserUpdateModel updateModel = new UserUpdateModel();
                updateModel.setUserName(userNameInput.getText());
                updateModel.setUserZipCode(userCep.getText());
                updateModel.setUserPhone(userPhone.getText());
                updateModel.setUserAddress(userAddress.getText());

                List<String> emptyFields = getEmptyFields(updateModel);

                if (!emptyFields.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String sql = "UPDATE bee_manager_system.tb_users" +
                                " SET" +
                                " nm_user = ?," +
                                " nr_phone = ?," +
                                " nr_cep = ?," +
                                " ds_address = ?" +
                                " WHERE id_user = ?;";

                        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
                        ps.setString(1, updateModel.getUserName());
                        ps.setString(2, updateModel.getUserPhone());
                        ps.setString(3, updateModel.getUserZipCode());
                        ps.setString(4, updateModel.getUserAddress());
                        ps.setInt(5, userModel.getUserId());
                        ps.executeUpdate();
                        ps.close();

                        JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso",
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
