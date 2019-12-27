package io.deki.autochrome.api.bridge;

import io.deki.autochrome.api.element.Element;

import java.util.*;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class JSVariableBridge {

    public static Map<String, Element> elementQueue = Collections.synchronizedMap(new HashMap<>());

    public static void onVariable(String id, String value) {
        System.out.println(id + ": " + value);
        Element element = elementQueue.getOrDefault(id, null);
        if (element == null) return;
        element.onJavaScriptValue(value);
        elementQueue.remove(id);
    }

}
