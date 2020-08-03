package io.deki.autochrome.client.wrapper;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefAuthCallback;
import org.cef.callback.CefRequestCallback;
import org.cef.handler.CefLoadHandler;
import org.cef.handler.CefRequestHandler;
import org.cef.handler.CefResourceRequestHandler;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
public class ProxyAuthenticationWrapper implements CefRequestHandler {

    private String username, password;

    public ProxyAuthenticationWrapper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean onBeforeBrowse(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, boolean b, boolean b1) {
        return false;
    }

    @Override
    public CefResourceRequestHandler getResourceRequestHandler(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, boolean b, boolean b1, String s, BoolRef boolRef) {
        return null;
    }

    @Override
    public boolean getAuthCredentials(CefBrowser cefBrowser, String s, boolean b, String s1, int i, String s2, String s3, CefAuthCallback cefAuthCallback) {
        System.out.println("Authenticating proxy with " + username + ":" + password);
        cefAuthCallback.Continue(username, password);
        return true;
    }

    @Override
    public boolean onQuotaRequest(CefBrowser cefBrowser, String s, long l, CefRequestCallback cefRequestCallback) {
        return false;
    }

    @Override
    public boolean onCertificateError(CefBrowser cefBrowser, CefLoadHandler.ErrorCode errorCode, String s, CefRequestCallback cefRequestCallback) {
        return false;
    }

    @Override
    public void onPluginCrashed(CefBrowser cefBrowser, String s) {

    }

    @Override
    public void onRenderProcessTerminated(CefBrowser cefBrowser, TerminationStatus terminationStatus) {

    }
}
