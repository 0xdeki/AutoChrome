package io.deki.autochrome.client;

import io.deki.autochrome.client.ui.ClientFrame;
import org.cef.CefApp;
import org.cef.handler.CefAppHandlerAdapter;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Boot {

    public static void main(String[] args) {
        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void stateHasChanged(CefApp.CefAppState state) {
                if (state == CefApp.CefAppState.TERMINATED) {
                    System.exit(0);
                }
            }
        });

        ClientFrame client = new ClientFrame();
        client.setSize(1200, 800);
        client.setLocationRelativeTo(null);
        client.setVisible(true);
    }

}
