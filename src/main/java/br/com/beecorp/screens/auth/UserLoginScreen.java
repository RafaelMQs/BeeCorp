package br.com.beecorp.screens.auth;

import br.com.beecorp.GlobalVariables;
import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.UserModel;
import br.com.beecorp.screens.sideMenu.SideMenuScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserLoginScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserLoginScreen.class.getName());

    public JPanel mainPanel;
    private JTextField userEmailInput;
    private JPasswordField userPasswordInput;
    private JButton loginButton;
    private JButton registerButton;

    public UserLoginScreen() {
        log.info("Open User Login Screen");

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserModel> users = getUsers();
                UserModel userLogin = getUserLogin(users);

                if (Objects.nonNull(userLogin)) {
                    GlobalVariables.userLogin = userLogin;

                    SideMenuScreen frame = new SideMenuScreen();
                    frame.setContentPane(frame.mainPanel);
                    createFrame(frame, "BeeCorp", true);
                    closeFrame(UserLoginScreen.this);
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha inválidos.",
                            "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegisterScreen frame = new UserRegisterScreen();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp", false);
                closeFrame(UserLoginScreen.this);
            }
        });
    }


    List<UserModel> getUsers() {
        String sql = "SELECT * FROM bee_manager_system.tb_users;";
        try {
            PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
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
                userModel.setUserZipCode(rs.getString("ds_address"));
                userModel.setUserCreationDate(rs.getDate("dt_creation"));
                userModels.add(userModel);
            }

            ps.close();
            rs.close();

            return userModels;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    UserModel getUserLogin(List<UserModel> userModels) {
        String userEmail = userEmailInput.getText();
        String userPassword = userPasswordInput.getText();

        UserModel userLogin = null;
        for (UserModel userModel : userModels) {
            if (Objects.equals(userModel.getUserEmail(), userEmail)
                    && Objects.equals(userModel.getUserPassword(), userPassword)) {
                userLogin = userModel;
                break;
            }
        }
        return userLogin;
    }

}
