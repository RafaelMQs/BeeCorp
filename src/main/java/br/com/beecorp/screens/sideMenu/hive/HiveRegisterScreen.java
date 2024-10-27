package br.com.beecorp.screens.sideMenu.hive;

import br.com.beecorp.jdbc.JdbcConnection;
import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.models.HiveRegisterModel;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HiveRegisterScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(HiveRegisterScreen.class.getName());

    private JTextField hiveStatusInput;
    public JPanel mainPanel;
    private JTextField hiveIdInput;
    private JTextField hiveCodeInput;
    private JFormattedTextField Date;
    private JTextField CodHive;
    private JTextField WeightHive;
    private JTextField UserID;
    private JButton sairButton;
    private JButton registrarButton;

    public HiveRegisterScreen() {
        log.info("Open Hive Register Screen");

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

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HiveScreen frame = new HiveScreen();
                frame.setContentPane(frame.mainPanel);
                createFrame(frame, "BeeCorp", false);
                closeFrame(HiveRegisterScreen.this);
            }
        });

        registrarButton.addActionListener(new ActionListener() {

                                              private static List<String> getEmptyFields(HiveRegisterModel registerModel) {
                                                  var fields = new ArrayList<Field>();
                                                  for (var field : HiveRegisterModel.class.getDeclaredFields()) {
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
                                                  return emptyFields;
                                              }

                                              @Override
                                              public void actionPerformed(ActionEvent e) {


                                                  HiveRegisterModel registerModel = new HiveRegisterModel();
//                                                  registerModel.setHiveStatusInput(hiveStatusInput.getText());
//                                                  registerModel.setHiveIdInput(hiveIdInput.getText());
//                                                  registerModel.setHiveCodeInput(hiveCodeInput.getText());
//                                                  registerModel.setDate(Date.getText());
//                                                  registerModel.setWeightHive(WeightHive.getText());
//                                                  registerModel.setUserID(UserID.getText());
//                                                  registerModel.setSairButton(sairButton.getText());
//                                                  registerModel.setRegistrarButton(registrarButton.getText());


                                                  List<String> emptyFields = getEmptyFields(registerModel);

                                                  if (!emptyFields.isEmpty()) {
                                                      JOptionPane.showMessageDialog(null, "Os Campos: " + emptyFields + " não podem ser vazíos",
                                                              "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                                                  }
                                                  try {
                                                      String sql = "INSERT INTO bee_manager_system.tb_hives (id_hive, id_user, cod_hive, nr_wight, st_hive, dt_creation)" +
                                                              "VALUES(?,?,?,?,?,?,?,?);";

                                                      PreparedStatement ps = JdbcConnection.getConnection().prepareStatement(sql);
//                                                      ps.setString(1, registerModel.getHiveIdInput());
//                                                      ps.setString(2, "NORMAL");
//                                                      ps.setString(3, registerModel.getUserID());
//                                                      ps.setString(4, registerModel.getHiveCodeInput());
//                                                      ps.setString(5, registerModel.getWeightHive());
//                                                      ps.setString(6, registerModel.getHiveStatusInput());
                                                      ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                                                      ps.executeUpdate();
                                                      ps.close();

                                                      JOptionPane.showMessageDialog(null, "Colmeia cadastrada com sucesso",
                                                              "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                                      HiveScreen frame = new HiveScreen();
                                                      frame.setContentPane(frame.mainPanel);
                                                      createFrame(frame, "BeeCorp", false);
                                                      closeFrame(HiveRegisterScreen.this);

                                                  } catch (SQLIntegrityConstraintViolationException ex) {
                                                      JOptionPane.showMessageDialog(null, "ID já cadastrado",
                                                              "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                                                  } catch (SQLException ex) {
                                                      ex.printStackTrace();
                                                      JOptionPane.showMessageDialog(null, "Houve um erro na conexão com o banco de dados",
                                                              "Alerta de Erro", JOptionPane.WARNING_MESSAGE);
                                                  }
                                              }
                                          }
        );

    }


}





