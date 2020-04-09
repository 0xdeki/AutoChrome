package io.deki.autochrome.client.script;

import io.deki.autochrome.api.common.Time;
import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.client.ui.ClientControlPanel;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ScriptThread extends Thread {

    private ClientControlPanel controlPanel;

    private Script script;

    private boolean started;

    public ScriptThread(ClientControlPanel controlPanel, ScriptEntry entry) {
        this.controlPanel = controlPanel;
        try {
            this.script = (Script) entry.getClazz().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!controlPanel.getStart().isEnabled()) {
            try {
                if (controlPanel.getPause().getText().equals("Resume")) {
                    Time.sleep(1000);
                    continue;
                }
                if (!started) {
                    started = script.start();
                    continue;
                }
                if (script.isStopping()) {
                    return;
                }
                Time.sleep(script.loop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
