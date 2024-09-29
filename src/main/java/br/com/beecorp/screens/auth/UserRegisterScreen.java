package br.com.beecorp.screens.auth;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.UserRegisterModel;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRegisterScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(UserRegisterScreen.class.getName());

    public JPanel mainPanel;
    private JTextField userNameInput;
    private JTextField userEmailInput;
    private JTextField userCep;
    private JTextField userPhone;
    private JTextField userConfirmationEmailInput;
    private JTextField userAddress;
    private JPasswordField userPassword;
    private JPasswordField userConfirmationInput;
    private JButton backButton;
    private JButton registerButton;

    public UserRegisterScreen() {
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoginScreen frame = new UserLoginScreen();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp");
                closeFrame(UserRegisterScreen.this);
            }
        });

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

                var fields = new ArrayList<Field>();
                for (var field : UserRegisterModel.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    fields.add(field);
                }

                List<String> emptyFields = new ArrayList<>();
                for (Field field : fields) {
                    try {
                        if (StringUtils.isNullOrEmpty(field.get(registerModel).toString())) {
                            emptyFields.add(field.getName());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (!emptyFields.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos",
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

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Houve um erro na conexão com o banco de dados",
                                "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                        ex.printStackTrace();
                    }
                }

            }
        });
    }
}
