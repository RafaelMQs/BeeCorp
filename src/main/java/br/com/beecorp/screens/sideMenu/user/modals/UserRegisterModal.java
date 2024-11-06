package br.com.beecorp.screens.sideMenu.user.modals;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.UserRegisterModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserRegisterModal extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserRegisterModal.class.getName());

    public JPanel mainPanel;
    private JTextField userNameInput;
    private JTextField userEmailInput;
    private JTextField userCep;
    private JTextField userPhone;
    private JTextField userConfirmationEmailInput;
    private JTextField userAddress;
    private JPasswordField userPassword;
    private JPasswordField userConfirmationInput;
    private JButton registerButton;

    public UserRegisterModal() {
        log.info("Open User Register Screen");

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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegisterModel registerModel = new UserRegisterModel();
                registerModel.setUserName(userNameInput.getText());
                registerModel.setUserEmail(userEmailInput.getText());
                registerModel.setUserConfirmationEmail(userConfirmationEmailInput.getText());
                registerModel.setUserZipCode(userCep.getText());
                registerModel.setUserPhone(userPhone.getText());
                registerModel.setUserAddress(userAddress.getText());
                registerModel.setUserPassword(userPassword.getText());
                registerModel.setUserConfirmationPassword(userConfirmationInput.getText());

                List<String> emptyFields = getEmptyFields(registerModel);

                if (!emptyFields.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else if (!Objects.equals(registerModel.getUserConfirmationEmail(), registerModel.getUserEmail())) {
                    JOptionPane.showMessageDialog(null, "O email de confirmação e o email devem ser iguais",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else if (!Objects.equals(registerModel.getUserConfirmationPassword(), registerModel.getUserPassword())) {
                    JOptionPane.showMessageDialog(null, "A senha de confirmação e a senha devem ser iguais",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String sql = "INSERT INTO bee_manager_system.tb_users(nm_user,user_role,ds_email,ps_password,nr_phone,nr_cep,ds_address,dt_creation)" +
                                "VALUES(?,?,?,?,?,?,?,?);";

                        PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
                        ps.setString(1, registerModel.getUserName());
                        ps.setString(2, "NORMAL");
                        ps.setString(3, registerModel.getUserEmail());
                        ps.setString(4, registerModel.getUserPassword());
                        ps.setString(5, registerModel.getUserPhone());
                        ps.setString(6, registerModel.getUserZipCode());
                        ps.setString(7, registerModel.getUserAddress());
                        ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
                        ps.executeUpdate();
                        ps.close();

                        JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null, "Email já cadastrado",
                                "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
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
