package io.deki.autochrome.api.element;

import io.deki.autochrome.api.Client;
import io.deki.autochrome.api.bridge.JSVariableBridge;
import io.deki.autochrome.api.common.Time;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class Element {

    private String javaScriptValue = null;

    private String name;
    private ElementType type;
    private int index;

    public Element(String name, ElementType type, int index) {
        this.name = name;
        this.type = type;
        this.index = index;
    }

    public Element(String name, ElementType type) {
        this(name, type, 0);
    }


    public String getValue() {
        return getAttribute("value");
    }

    public String getType() {
        return getAttribute("type");
    }

    public void click() {
        callMethod("click");
    }

    public void setValue(Object value) {
        String code = getElementCall() + ".value = '" + value.toString() + "';";
        Client.execute(code);
    }

    public void triggerEvent(String eventName) {
        String code = getElementCall() + ".dispatchEvent(new Event('" + eventName + "'));";
        Client.execute(code);
    }

    public boolean isDefined() {
        String code = wrapInPrompt(getElementCall() + " != undefined");
        Client.execute(code);
        return Boolean.parseBoolean(getJavaScriptValue());
    }

    public void callMethod(String methodName) {
        String code = getElementCall() + "." + methodName + "();";
        Client.execute(code);
    }

    public String getAttribute(String attribute) {
        String code = wrapInPrompt(getElementCall() + "." + attribute);
        Client.execute(code);
        return getJavaScriptValue();
    }

    public String getElementCall() {
        switch (getElementType()) {
            case ID: return String.format("document.getElementById('%s')", getName());
            case NAME: return String.format("document.getElementsByName('%s')[%s]", getName(), getIndex());
            case CLASS_NAME: return String.format("document.getElementsByClassName('%s')[%s]", getName(), getIndex());
            case TAG_NAME: return String.format("document.getElementsByTagName('%s')[%s]", getName(), getIndex());
        }
        return null;
    }

    public String wrapInPrompt(String code) {
        return String.format("prompt('AutoChrome %s ' + (%s))", getId(), code);
    }

    public String getJavaScriptValue() {
        JSVariableBridge.elementQueue.put(getId(), this);
        Time.waitFor(() -> javaScriptValue != null, 2000);
        String value = javaScriptValue;
        javaScriptValue = null;
        return value;
    }

    public void onJavaScriptValue(String value) {
        javaScriptValue = value;
    }

    public String getId() {
        return getName().replaceAll(" ", "^") + getIndex() + getElementType().name();
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public ElementType getElementType() {
        return type;
    }

    public enum ElementType {
        ID,
        NAME,
        CLASS_NAME,
        TAG_NAME
    }

}
