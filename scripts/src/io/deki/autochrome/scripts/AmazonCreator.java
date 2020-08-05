package io.deki.autochrome.scripts;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.common.Random;
import io.deki.autochrome.api.common.Time;
import io.deki.autochrome.api.element.Element;
import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.api.script.ScriptMeta;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
@ScriptMeta(name = "Amazon", author = "Deki", description = "Creates amazon accs", version = 1.0)
public class AmazonCreator extends Script {

    @Override
    public int loop() {
        if (!Client.getUrl().contains("amazon.com")) {
            Client.loadUrl("https://www.amazon.com/");
            waitForPageLoad();
            return 1000;
        }

        Element mainPage = new Element("nav-flyout-ya-newCust", Element.ElementType.ID).getChild(0);
        if (mainPage.isDefined()) {
            mainPage.click();
            waitForPageLoad();
            return 1000;
        }

        Element name = new Element("ap_customer_name", Element.ElementType.ID);
        if (name.isDefined()) {
            name.setValue("Name Nameson");
            Time.sleep(500);
        }

        Element email = new Element("ap_email", Element.ElementType.ID);
        if (email.isDefined()) {
            email.setValue(Random.nextAlphaNumeric(12) + "@speediest.email");
            Time.sleep(500);
        }

        String pswd = Random.nextAlphaNumeric(12);

        Element password = new Element("ap_password", Element.ElementType.ID);
        if (password.isDefined()) {
            password.setValue(pswd);
            Time.sleep(500);
        }

        Element passwordCheck = new Element("ap_password_check", Element.ElementType.ID);
        if (passwordCheck.isDefined()) {
            passwordCheck.setValue(pswd);
            Time.sleep(500);
        }

        Element createButton = new Element("continue", Element.ElementType.ID);
        if (createButton.isDefined()) {
            createButton.click();
            waitForPageLoad();
            return 1000;
        }

        return 1000;
    }
}
