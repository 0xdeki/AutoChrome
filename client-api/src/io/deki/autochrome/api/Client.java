package io.deki.autochrome.api;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

import java.awt.*;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Client {

    private static CefApp instance;

    private static CefSettings settings;

    private static CefClient client;

    private static CefBrowser browser;

    private static Component component;

    public static void init(CefApp instance, CefSettings settings, CefClient client, CefBrowser browser, Component component) {
        Client.instance = instance;
        Client.settings = settings;
        Client.client = client;
        Client.browser = browser;
        Client.component = component;
    }

    public static void execute(String javaScript) {
        System.out.println("Executing: " + javaScript);
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
