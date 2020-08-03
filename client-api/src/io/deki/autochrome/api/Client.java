package io.deki.autochrome.api;

import io.deki.autochrome.api.callback.BrowserCallback;
import io.deki.autochrome.api.common.Time;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Client {

    private static List<BrowserCallback> browserCallbacks = new ArrayList<>();

    private static CefApp instance;

    private static CefSettings settings;

    private static CefClient client;

    private static CefBrowser browser;

    private static Component component;

    public static void init(CefApp instance, CefSettings settings, CefClient client) {
        Client.instance = instance;
        Client.settings = settings;
        Client.client = client;

        browser = client.createBrowser("https://google.com", false, false);
        component = browser.getUIComponent();

        for (BrowserCallback browserCallback : browserCallbacks) {
            browserCallback.onBrowserChanged(browser);
        }
    }

    public static void register(BrowserCallback callback) {
        browserCallbacks.add(callback);
    }

    public static void exit() {
        CefApp.getInstance().dispose();
    }

    public static void execute(String javaScript) {
        getBrowser().executeJavaScript(javaScript, "", 0);
    }

    public static String getUrl() {
        return getBrowser().getURL();
    }

    public static void loadUrl(String url) {
        getBrowser().loadURL(url);
    }

    public static CefClient getClient() {
        return client;
    }

    public static CefBrowser getBrowser() {
        return browser;
    }

    public static CefSettings getSettings() {
        return settings;
    }

    public static CefApp getInstance() {
        return instance;
    }

    public static Component getComponent() {
        return component;
    }
}
