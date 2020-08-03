package io.deki.autochrome.client.ui;

import io.deki.autochrome.api.script.ScriptMeta;
import io.deki.autochrome.client.script.ScriptEntry;
import io.deki.autochrome.client.script.ScriptLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ScriptFrame extends JFrame {

    private JPanel root = new JPanel(new BorderLayout());

    private JTable scripts;

    private JButton start;

    public ScriptFrame(ClientControlPanel parent) {
        ScriptLoader loader = new ScriptLoader();
        List<ScriptEntry> scriptEntries = loader.loadAll();
        String[] titles = new String[]{"Name", "Author", "Description", "Version"};
        Object[][] data = new Object[scriptEntries.size()][4];
        for (int i = 0; i < scriptEntries.size(); i++) {
            ScriptMeta manifest = scriptEntries.get(i).getManifest();
            data[i] = new Object[]{
                    manifest.name(),
                    manifest.author(),
                    manifest.description(),
                    manifest.version()
            };
        }
        scripts = new JTable(data, titles);
        scripts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        start = new JButton("Start");
        start.addActionListener(e -> {
            int index = scripts.getSelectedRow();
            String name = (String) data[index][0];
            scriptEntries.stream().filter(x -> x.getManifest().name().equals(name)).findFirst()
                    .ifPresent(script -> parent.launchScript(this, script));
        });

        setTitle("Script selector");
        add(new JScrollPane(scripts), BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);
        pack();
    }

}
