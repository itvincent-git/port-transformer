package net.port.transformer.compiler.data;

import javax.lang.model.element.Element;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceMethod {
    public Element element;
    public String name;
    public PortInterfaceData portInterfaceData;

    public PortInterfaceMethod(Element element, String name, PortInterfaceData portInterfaceData) {
        this.element = element;
        this.name = name;
        this.portInterfaceData = portInterfaceData;
    }
}
