package br.com.beecorp.screens.sideMenu;

import br.com.beecorp.models.DefaultScreenAbstract;
import br.com.beecorp.models.DefaultScreenInterface;
import br.com.beecorp.screens.sideMenu.hive.HiveScreen;
import br.com.beecorp.screens.sideMenu.user.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SideMenuScreen extends DefaultScreenAbstract implements DefaultScreenInterface {
    private static final Logger log = Logger.getLogger(SideMenuScreen.class.getName());

    public JPanel mainPanel;
    private JPanel sideBarPanel;
    private JButton hiveScreenButton;
    private JButton userScreenButton;
    private JPanel contentPanel;
    private JPanel content;
    private JButton logoutButton;

    public SideMenuScreen() {
        log.info("Open Side Menu Screen");

        createUIComponents();
        actionListeners();
    }

    @Override
    public void createUIComponents() {
        // TODO: place custom component creation code here
        this.setSize(600, 600);

        prepareToAddNewContainer(content);
        content.add(new UserScreen().mainPanel);
    }

    @Override
    public void actionListeners() {
        // TODO: place button actions component creation code here
        userScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareToAddNewContainer(content);
                content.add(new UserScreen().mainPanel);
            }
        });

        hiveScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareToAddNewContainer(content);
                content.add(new HiveScreen().mainPanel);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void prepareToAddNewContainer(JPanel content){
        content.removeAll();
        content.repaint();
        content.revalidate();
    }
}
