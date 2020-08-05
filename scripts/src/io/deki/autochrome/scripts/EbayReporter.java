package io.deki.autochrome.scripts;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.common.Time;
import io.deki.autochrome.api.element.Element;
import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.api.script.ScriptMeta;

import javax.swing.*;

/**
 * @author Endre on 09.04.2020
 * @project AutoChrome
 **/

@ScriptMeta(name = "Ebay Reporter", author = "Deki", description = "Repots pokemon go listings", version = 1.0)
public class EbayReporter extends Script {

    int offset = 1;

    String username, password;

    public boolean start() {
        Client.getSettings().user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0";
        username = JOptionPane.showInputDialog(null, "Ebay username:", "Ebay reporter", JOptionPane.INFORMATION_MESSAGE);
        password = JOptionPane.showInputDialog(null, "Ebay password:", "Ebay reporter", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    @Override
    public int loop() {
        if (!Client.getUrl().contains("ebay.com") && !Client.getUrl().contains("ebay.co.uk")) {
            Client.loadUrl("https://www.ebay.com");
            waitForPageLoad();
            return 100;
        }

        if (Client.getUrl().contains("signin.ebay") || Client.getUrl().contains("signin/s")) {
            if (username == null || password == null) {
                return 1000;
            }

            Element element = new Element("userid", Element.ElementType.ID);
            if (!element.isDefined()) {
                return 100;
            }
            element.setValue(username);
            Time.sleep(200);

            element = new Element("pass", Element.ElementType.ID);
            if (!element.isDefined()) {
                return 100;
            }
            element.setValue(password);
            Time.sleep(200);

            element = new Element("sgnBt", Element.ElementType.ID);
            if (!element.isDefined()) {
                return 100;
            }
            element.click();

            waitForPageLoad();
            return 100;
        }

        if (Client.getUrl().contains("ocswf.ebay")) {
            if (Client.getUrl().contains("ebay.com")) {
                Client.loadUrl(Client.getUrl().replace("ebay.com", "ebay.co.uk"));
                waitForPageLoad();
                return 100;
            }
            if (Client.getUrl().contains("rti/send")) {
                ++offset;
                Client.loadUrl("https://www.ebay.com");
                waitForPageLoad();
                return 100;
            }

            Element element = new Element("[op-value='1518']", Element.ElementType.QUERY);
            if (!element.isDefined()) {
                return 100;
            }
            element.click();
            Time.sleep(200);

            element = new Element("[op-value='1591']", Element.ElementType.QUERY);
            if (!element.isDefined()) {
                return 100;
            }
            element.click();
            Time.sleep(200);

            element = new Element("[op-value='1594']", Element.ElementType.QUERY);
            if (!element.isDefined()) {
                return 100;
            }
            element.click();
            Time.sleep(2000);

            element = new Element("snd-btn", Element.ElementType.CLASS_NAME, 1);
            if (!element.isDefined()) {
                return 1000;
            }
            element.click();
            waitForPageLoad();
            return 100;
        }

        if (Client.getUrl().contains("/itm/")) {
            if (!Client.getUrl().toLowerCase().contains("pokemon")) {
                ++offset;
                Client.loadUrl("https://www.ebay.com");
                waitForPageLoad();
                return 100;
            }
            Element element = new Element("pglv-pr-rep", Element.ElementType.CLASS_NAME);
            for (int i = 0; i < 5; i++) {
                if (element.getChild(i).isDefined() && element.getChild(i).getAttribute("href").contains("dll")) {
                    Client.loadUrl(element.getChild(i).getAttribute("href"));
                    waitForPageLoad();
                    break;
                }
            }
            return 200;
        }

        if (!Client.getUrl().contains("/sch/")) {
            Element element = new Element("gh-ac", Element.ElementType.ID);
            if (!element.isDefined()) {
                return 200;
            }
            if (!element.getValue().equalsIgnoreCase("pokemon go accounts")) {
                element.setValue("pokemon go accounts");
                return 200;
            }
            element = new Element("gh-btn", Element.ElementType.ID);
            if (!element.isDefined()) {
                return 200;
            }
            element.click();
            waitForPageLoad();
            return 100;
        }

        int page = offset / 49;
        int index = offset % 49;
        ++page;

        System.out.println("offset: " + offset + ", page: " + page + ", index " + index);

        String pageUrl = "&_pgn=" + page;

        if (!Client.getUrl().contains(pageUrl)) {
            Client.loadUrl(Client.getUrl() + pageUrl);
            waitForPageLoad();
            return 100;
        }

        Element element = new Element("s-item__link", Element.ElementType.CLASS_NAME, index);
        if (element.isDefined()) {
            element.click();
            waitForPageLoad();
            return 100;
        }

        element = new Element("lvtitle", Element.ElementType.CLASS_NAME, index);
        if (element.isDefined()) {
            Client.loadUrl(element.getAttribute("href"));
            waitForPageLoad();
            return 100;
        }

        return 1000;
    }
}
