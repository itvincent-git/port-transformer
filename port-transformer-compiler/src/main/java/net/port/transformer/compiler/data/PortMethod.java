package net.port.transformer.compiler.data;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

/**
 * represent the @PortInterface method like > SampleReport.report()
 * Created by zhongyongsheng on 2018/4/16.
 */
public class PortMethod {
    public ExecutableElement executableElement;
    public List<PortMethodParameter> portMethodParameterList;
    public TypeMirror processorTypeMirror;

    public PortMethod(ExecutableElement executableElement, List<PortMethodParameter> portMethodParameterList, TypeMirror processorTypeMirror) {
        this.executableElement = executableElement;
        this.portMethodParameterList = portMethodParameterList;
        this.processorTypeMirror = processorTypeMirror;
    }

}
