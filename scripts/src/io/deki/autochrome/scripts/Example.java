package io.deki.autochrome.scripts;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.element.Element;
import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.api.script.ScriptMeta;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/

@ScriptMeta(name = "Example", author = "Deki", description = "Test", version = 1.0)
public class Example extends Script {

    @Override
    public int loop() {
        if (!Client.getUrl().contains("github.com")) {
            Client.loadUrl("https://github.com/");
        }
        if (Client.getUrl().contains("AutoChrome")) {
            return 1000;
        }
        Element searchBar = new Element("header-search-input", Element.ElementType.CLASS_NAME, 0);
        if (searchBar.isDefined()) {
            searchBar.setValue("AutoChrome");
        }
        Element form = new Element("js-site-search-form", Element.ElementType.CLASS_NAME, 0);
        if (form.isDefined()) {
            form.callMethod("submit");
        }
        return 1000;
    }
}
