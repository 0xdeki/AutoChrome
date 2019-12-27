package io.deki.autochrome.client.ui;

import io.deki.autochrome.client.script.ScriptEntry;
import io.deki.autochrome.client.script.ScriptThread;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ClientControlPanel extends JPanel {

    private JButton start;

    private JButton pause;

    private JButton stop;

    public ClientControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public void init() {
        start = new JButton("Start");
        start.addActionListener(e -> {
            ScriptFrame frame = new ScriptFrame(this);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        add(start);

        pause = new JButton("Pause");
        pause.setEnabled(false);
        pause.addActionListener(e -> {
            if (pause.getText().equals("Pause")) {
                pause.setText("Resume");
            } else {
                pause.setText("Pause");
            }
        });
        add(pause);

        stop = new JButton("Stop");
        stop.setEnabled(false);
        stop.addActionListener(e -> {
            pause.setText("Pause");
            stop.setEnabled(false);
            pause.setEnabled(false);
            start.setEnabled(true);
        });
        add(stop);
    }

    public void launchScript(ScriptFrame frame, ScriptEntry script) {
        frame.dispose();
        ScriptThread thread = new ScriptThread(this, script);
        start.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
        Executors.newSingleThreadExecutor().execute(thread);
    }

    public JButton getPause() {
        return pause;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getStop() {
        return stop;
    }
}
