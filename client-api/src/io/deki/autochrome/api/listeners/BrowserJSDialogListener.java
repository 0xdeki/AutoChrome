package io.deki.autochrome.api.listeners;

import io.deki.autochrome.api.bridge.JSVariableBridge;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefJSDialogCallback;
import org.cef.handler.CefJSDialogHandler;
import org.cef.misc.BoolRef;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class BrowserJSDialogListener implements CefJSDialogHandler {

    @Override
    public boolean onJSDialog(CefBrowser browser, String url, JSDialogType type, String text, String s2, CefJSDialogCallback callback, BoolRef suppress) {
        if (type.equals(JSDialogType.JSDIALOGTYPE_PROMPT) && text.startsWith("AutoChrome ")) {
            String message = text.substring(text.indexOf("AutoChrome ") + 11);
            if (!message.contains(" ")) return false;
            String id = message.split(" ")[0];
            String value = message.substring(message.indexOf(" ") + 1);
            JSVariableBridge.onVariable(id, value);
            suppress.set(true);
        }
        return false;
    }

    @Override
    public boolean onBeforeUnloadDialog(CefBrowser cefBrowser, String s, boolean b, CefJSDialogCallback cefJSDialogCallback) {
        return false;
    }

    @Override
    public void onResetDialogState(CefBrowser cefBrowser) {

    }

    @Override
    public void onDialogClosed(CefBrowser cefBrowser) {

    }
}
