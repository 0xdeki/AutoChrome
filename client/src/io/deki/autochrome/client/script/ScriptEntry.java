package io.deki.autochrome.client.script;

import io.deki.autochrome.api.script.ScriptMeta;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ScriptEntry {

    private Class clazz;

    private ScriptMeta manifest;

    public ScriptEntry(Class clazz, ScriptMeta manifest) {
        this.clazz = clazz;
        this.manifest = manifest;
    }

    public Class getClazz() {
        return clazz;
    }

    public ScriptMeta getManifest() {
        return manifest;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setManifest(ScriptMeta manifest) {
        this.manifest = manifest;
    }
}
