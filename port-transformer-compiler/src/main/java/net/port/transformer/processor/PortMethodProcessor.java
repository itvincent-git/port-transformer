package net.port.transformer.processor;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.common.Log;
import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.compiler.data.PortMethod;
import net.port.transformer.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
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
        compileContext.log.warn(element, "PortMethodProcessor %s", element.getSimpleName());
    }

    PortMethod process() {
        AnnotationMirror portProcessorMirror = Util.getAnnotationMirror(element, PortProcessor.class);
        if (portProcessorMirror == null)
            compileContext.log.error(element, "cannot find @PortProcessor");
        AnnotationValue processorValue = Util.getAnnotationValue(portProcessorMirror, "processor");
        TypeMirror processorTypeMirror = Util.annotationValueToType(processorValue);
        compileContext.log.warn(element, "PortMethodProcessor process%s", processorTypeMirror);

        PortMethod portMethod = new PortMethod();
        return portMethod;
    }
}
