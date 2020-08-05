package io.deki.autochrome.api.common;

import io.deki.autochrome.api.Client;
import org.cef.network.CefCookie;
import org.cef.network.CefCookieManager;

import java.util.Date;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
public class Cookies {

    public static void deleteAllCookies() {
        CefCookieManager.getGlobalManager().deleteCookies("", "");
    }

    public static void deleteCookiesByUrl(String url) {
        CefCookieManager.getGlobalManager().deleteCookies(url, "");
    }

    public static void createCookie(String name, String value) {
        String currentUrl = Client.getUrl();
        String protocol = currentUrl.substring(0, currentUrl.indexOf("://"));
        String domain = currentUrl.substring(currentUrl.indexOf("://") + 3);
        domain = domain.substring(0, domain.indexOf("/"));
        String root = domain;
        String last = "";
        while (root.contains(".")) {
            last = root.substring(0, root.indexOf("."));
            root = root.substring(root.indexOf(".") + 1);
        }
        root = "." + last + "." + root;
        Date now = new Date();
        Date expires = new Date(now.getTime() + 86400000);
        CefCookie cookie = new CefCookie(name, value, root, "/", protocol.contains("https"),
                false, now, now, true, expires);
        CefCookieManager.getGlobalManager().setCookie(protocol + "://" + domain, cookie);
    }

}
