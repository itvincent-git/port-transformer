package net.port.transformer.compiler.data;

import javax.lang.model.element.VariableElement;

/**
 * Created by zhongyongsheng on 2018/4/16.
 */
public class PortMethodParameter {
    public VariableElement element;
    public String parameterKey;

    public PortMethodParameter(VariableElement element, String parameterKey) {
        this.element = element;
        this.parameterKey = parameterKey;
    }
}