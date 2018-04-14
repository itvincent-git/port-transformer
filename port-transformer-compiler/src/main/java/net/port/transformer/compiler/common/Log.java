package net.port.transformer.compiler.common;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class Log {
    private Messager messager;
    private Element defaultElement;

    public Log(Messager messager, Element defaultElement) {
        this.messager = messager;
        this.defaultElement = defaultElement;
    }

    public void debug(Element element, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, safeFormat(msg, args), element);
    }

    public void debug(String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, safeFormat(msg, args));
    }

    public void error(Element element, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, safeFormat(msg, args), element);
    }

    public void error(String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, safeFormat(msg, args));
    }

    private String safeFormat(String msg, Object... args) {
        try {
            return String.format(msg, args);
        } catch (Exception e) {
            return msg;
        }
    }
}
