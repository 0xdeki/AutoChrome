package io.deki.autochrome.api.script;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.common.Time;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public abstract class Script {

    private boolean stopping = false;

    public boolean start() {
        return true;
    }

    public abstract int loop();

    public void onStart(String[] args) {

    }

    public boolean isStopping() {
        return stopping;
    }

    public void setStopping(boolean stopping) {
        this.stopping = stopping;
    }

    public void waitForPageLoad() {
        Time.waitFor(() -> Client.getBrowser().isLoading(), 9000, 200);
        Time.waitFor(() -> !Client.getBrowser().isLoading(), 9000, 200);
    }
}
