package io.deki.autochrome.api.script;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface ScriptMeta {

    String name();

    String author();

    String description();

    double version();

}
