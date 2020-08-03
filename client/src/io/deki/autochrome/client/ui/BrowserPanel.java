package io.deki.autochrome.client.ui;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.callback.BrowserCallback;
import io.deki.autochrome.api.listeners.BrowserJSDialogListener;
import io.deki.autochrome.api.listeners.BrowserMouseListener;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class BrowserPanel extends JPanel implements BrowserCallback {

    private ClientFrame parent;

    private boolean focused;

    private CefSettings settings;

    private CefApp instance;

    private CefClient client;

    private CefBrowser browser;

    private Component browserUI;

    public BrowserPanel(ClientFrame parent) {
        setLayout(new BorderLayout());
        this.parent = parent;
    }

    public void init() {
        //create the browser, settings can only be passed once
        settings = new CefSettings();
        settings.windowless_rendering_enabled = false;
        instance = CefApp.getInstance(settings);
        client = instance.createClient();

        //init api
        Client.register(this);
        Client.init(instance, settings, client);
    }

    @Override
    public void onBrowserChanged(CefBrowser cefBrowser) {
        if (browserUI != null) {
            remove(browserUI);
        }

        browser = cefBrowser;
        browserUI = browser.getUIComponent();

        //add handlers
        getClient().addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                getParent().getAddress().setText(url);
            }
        });

        getClient().addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
            @Override
            public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String url, String s1) {
                System.out.println("Popup: " + url);
                return true;
            }
        });

        getParent().getAddress().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!focused) return;
                focused = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                getParent().getAddress().requestFocus();
            }
        });

        getClient().addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (focused) return;
                focused = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                focused = false;
            }
        });

        getClient().addJSDialogHandler(new BrowserJSDialogListener());
        getParent().getAddress().addActionListener(e -> {
            if (getParent().getAddress().getText().startsWith("http")) {
                getBrowser().loadURL(getParent().getAddress().getText());
            } else {
                getBrowser().loadURL("https://www.google.com/search?q=" + getParent().getAddress().getText());
            }
        });

        add(browserUI, BorderLayout.CENTER);

        BrowserMouseListener mouseListener = new BrowserMouseListener();

        browserUI.addMouseListener(mouseListener);
        browserUI.addMouseMotionListener(mouseListener);
    }

    @Override
    public ClientFrame getParent() {
        return parent;
    }

    public CefBrowser getBrowser() {
        return browser;
    }

    public CefClient getClient() {
        return client;
    }

    public Component getBrowserUI() {
        return browserUI;
    }

    public boolean isFocused() {
        return focused;
    }

}
