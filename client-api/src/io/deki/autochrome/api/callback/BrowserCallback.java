package io.deki.autochrome.api.callback;

import org.cef.browser.CefBrowser;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
public interface BrowserCallback {

    void onBrowserChanged(CefBrowser browser);

}
