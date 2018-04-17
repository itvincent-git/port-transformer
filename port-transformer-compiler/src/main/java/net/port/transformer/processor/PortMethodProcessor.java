package net.port.transformer.processor;

import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortMethod;
import net.port.transformer.compiler.data.PortMethodParameter;
import net.port.transformer.util.Util;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortMethodProcessor {

    CompilerContext compileContext;
    ExecutableElement element;

    PortMethodProcessor(CompilerContext context, ExecutableElement element) {
        compileContext = context;
        this.element = element;
    }

    PortMethod process() {
        AnnotationMirror portProcessorMirror = Util.getAnnotationMirror(element, PortProcessor.class);
        if (portProcessorMirror == null)
            compileContext.log.error(element, "cannot find @PortProcessor");
        AnnotationValue processorValue = Util.getAnnotationValue(portProcessorMirror, "processor");
        TypeMirror processorTypeMirror = Util.annotationValueToType(processorValue);
        List<PortMethodParameter> portMethodParameterList = new PortMethodParameterProcessor(compileContext, element).process();
        PortMethod portMethod = new PortMethod(element, portMethodParameterList, processorTypeMirror);
        return portMethod;
    }
}
