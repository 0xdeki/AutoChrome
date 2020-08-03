package io.deki.autochrome.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
public class ClientMenu extends JMenuBar {

    private JFrame devToolsFrame;

    public ClientMenu(ClientFrame clientFrame) {
        JMenuItem devTools = new JMenuItem("Dev tools");
        devTools.addActionListener(e -> {
            if (devToolsFrame != null && devToolsFrame.isVisible()) {
                return;
            }
            devToolsFrame = new JFrame();
            devToolsFrame.getContentPane().add(clientFrame.getBrowserPanel().getBrowser().getDevTools().getUIComponent(), BorderLayout.CENTER);
            devToolsFrame.setSize(1200, 400);
            devToolsFrame.setTitle("Developer tools");
            devToolsFrame.setLocationRelativeTo(null);
            devToolsFrame.setVisible(true);
        });
        add(devTools);
    }

}
