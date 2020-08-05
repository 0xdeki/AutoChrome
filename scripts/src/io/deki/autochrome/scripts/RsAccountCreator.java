package io.deki.autochrome.scripts;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.common.Cookies;
import io.deki.autochrome.api.common.Random;
import io.deki.autochrome.api.common.Time;
import io.deki.autochrome.api.element.Element;
import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.api.script.ScriptMeta;

/**
 * @author Deki on 03.08.2020
 * @project AutoChrome
 **/
@ScriptMeta(name = "rsaccs", author = "Deki", description = "Creates accounts", version = 1.0)
public class RsAccountCreator extends Script {

    private String mail, pswd;

    @Override
    public void onStart(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-email")) {
                mail = args[i + 1];
                System.out.println("Set mail: " + mail);
            }
            if (args[i].equalsIgnoreCase("-password")) {
                pswd = args[i + 1];
                System.out.println("Set password: " + pswd);
            }
        }
    }

    @Override
    public int loop() {
        if (!Client.getUrl().contains("secure.runescape.com")) {
            Client.loadUrl("https://secure.runescape.com/m=account-creation/g=oldscape/create_account");
            waitForPageLoad();
            return 1000;
        }

        if (Client.getUrl().contains("https://secure.runescape.com/m=account-creation/g=oldscape/error?")) {
            return 1000;
        }

        if (Client.getUrl().contains("account_created")) {
            System.out.println("CREATED ACCOUNT " + mail + ":" + pswd);
            Client.exit();
            System.exit(0);
            return 1000;
        }

        if (mail == null) {
            mail = Random.nextAlphaNumeric(12) + "@gmail.com";
        }
        if (pswd == null) {
            pswd = Random.nextAlphaNumeric(12);
        }

        Element email = new Element("create-email", Element.ElementType.ID);
        if (!email.isDefined()) {
            return 1000;
        }
        email.setValue(mail);

        Time.sleep(500);

        Element password = new Element("create-password", Element.ElementType.ID);
        if (!password.isDefined()) {
            return 1000;
        }
        password.setValue(pswd);

        Element day = new Element("m-date-entry__day-field", Element.ElementType.CLASS_NAME);
        if (!day.isDefined()) {
            return 1000;
        }
        day.setValue(Random.nextInt(1, 27));

        Element month = new Element("m-date-entry__month-field", Element.ElementType.CLASS_NAME);
        if (!month.isDefined()) {
            return 1000;
        }
        month.setValue(Random.nextInt(1, 12));

        Element year = new Element("m-date-entry__year-field", Element.ElementType.CLASS_NAME);
        if (!year.isDefined()) {
            return 1000;
        }
        year.setValue(Random.nextInt(1970, 2000));

        Time.sleep(500);

        Element create = new Element("create-submit", Element.ElementType.ID);
        if (!create.isDefined()) {
            return 1000;
        }
        create.click();

        waitForPageLoad();

        return 1000;
    }
}
