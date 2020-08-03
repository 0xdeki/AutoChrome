package io.deki.autochrome.client;

import io.deki.autochrome.client.script.ScriptEntry;
import io.deki.autochrome.client.script.ScriptLoader;
import io.deki.autochrome.client.ui.ClientFrame;
import io.deki.autochrome.client.wrapper.ProxyAuthenticationWrapper;
import org.cef.CefApp;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefAuthCallback;
import org.cef.callback.CefCommandLine;
import org.cef.callback.CefRequestCallback;
import org.cef.handler.CefAppHandlerAdapter;
import org.cef.handler.CefLoadHandler;
import org.cef.handler.CefRequestHandler;
import org.cef.handler.CefResourceRequestHandler;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Boot {

    private static String quickstartScript, proxy, proxyUsername, proxyPassword;

    public static void main(String[] args) {
        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void onBeforeCommandLineProcessing(String var1, CefCommandLine var2) {
                if (proxy != null) {
                    var2.appendSwitchWithValue("proxy-server", proxy);
                }
            }
            @Override
            public void stateHasChanged(CefApp.CefAppState state) {
                if (state == CefApp.CefAppState.INITIALIZED) {
                    System.out.println("Loaded client");
                }
                if (state == CefApp.CefAppState.TERMINATED) {
                    System.exit(0);
                }
            }
        });

        handleArgs(args);

        ClientFrame client = new ClientFrame();
        client.setSize(1200, 800);
        client.setLocationRelativeTo(null);
        client.setVisible(true);

        if (proxyUsername != null && proxyPassword != null) {
            client.getBrowserPanel().getClient().addRequestHandler(new ProxyAuthenticationWrapper(proxyUsername, proxyPassword));
        }

        if (quickstartScript != null) {
            ScriptLoader loader = new ScriptLoader();
            List<ScriptEntry> scriptEntries = loader.loadAll();
            scriptEntries.stream().filter(x -> x.getManifest().name().equalsIgnoreCase(quickstartScript))
                    .findFirst().ifPresent(script -> client.getControlPanel().launchScript(null, script));
        }
    }

    public static void handleArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-script")) {
                quickstartScript = args[i + 1];
            }
            if (args[i].equalsIgnoreCase("-proxy")) {
                proxy = args[i + 1];
            }
            if (args[i].equalsIgnoreCase("-proxyUsername")) {
                proxyUsername = args[i + 1];
            }
            if (args[i].equalsIgnoreCase("-proxyPassword")) {
                proxyPassword = args[i + 1];
            }
        }
    }

    static class ProxyAuth extends Authenticator {

        private PasswordAuthentication auth;

        public ProxyAuth(String user, String password) {
            auth = new PasswordAuthentication(user, password == null ? new char[]{} : password.toCharArray());
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return auth;
        }
    }

}
