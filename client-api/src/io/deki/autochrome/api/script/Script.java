package io.deki.autochrome.api.script;

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

    public boolean isStopping() {
        return stopping;
    }

    public void setStopping(boolean stopping) {
        this.stopping = stopping;
    }
}
