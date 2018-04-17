package net.port.transformer.compiler.data;

import java.util.List;

import javax.lang.model.element.ExecutableElement;

/**
 * represent the @PortInterface method like > SampleReport.report()
 * Created by zhongyongsheng on 2018/4/16.
 */
public class PortMethod {
    public ExecutableElement executableElement;
    public List<PortMethodParameter> portMethodParameterList;

    public PortMethod(ExecutableElement executableElement, List<PortMethodParameter> portMethodParameterList) {
        this.executableElement = executableElement;
        this.portMethodParameterList = portMethodParameterList;
    }

}
