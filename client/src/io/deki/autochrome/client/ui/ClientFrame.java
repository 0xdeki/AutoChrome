package io.deki.autochrome.client.ui;

import org.cef.CefApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ClientFrame extends JFrame {

    private JPanel root = new JPanel(new BorderLayout());

    private JTextField address;

    private BrowserPanel browserPanel;

    private ClientControlPanel controlPanel;

    private ClientGlassPanel glassPane;

    public ClientFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
            }
        });

        address = new JTextField();

        browserPanel = new BrowserPanel(this);
        browserPanel.init();

        controlPanel = new ClientControlPanel();
        controlPanel.init();

        root.add(address, BorderLayout.NORTH);
        root.add(browserPanel, BorderLayout.CENTER);
        root.add(controlPanel, BorderLayout.SOUTH);

        glassPane = new ClientGlassPanel();
        setGlassPane(glassPane);
        glassPane.setVisible(true);

        setContentPane(root);
        setJMenuBar(new ClientMenu(this));
        setTitle("AutoChrome");
        pack();
    }

    public BrowserPanel getBrowserPanel() {
        return browserPanel;
    }

    public ClientControlPanel getControlPanel() {
        return controlPanel;
    }

    public JTextField getAddress() {
        return address;
    }

    public JPanel getRoot() {
        return root;
    }
}
