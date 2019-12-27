package io.deki.autochrome.api.script;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public abstract class Script {

    public boolean start() {
        return true;
    }

    public abstract int loop();

}
