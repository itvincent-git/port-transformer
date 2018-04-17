package net.port.transformer.processor;

import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortMethod;
import net.port.transformer.compiler.data.PortMethodParameter;
import net.port.transformer.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortMethodParameterProcessor {

    CompilerContext compileContext;
    ExecutableElement element;

    PortMethodParameterProcessor(CompilerContext context, ExecutableElement element) {
        compileContext = context;
        this.element = element;
        compileContext.log.warn(element, "PortMethodParameterProcessor %s", element.getSimpleName());
    }

    List<PortMethodParameter> process() {

        List<? extends VariableElement> parameters = element.getParameters();
        return parameters.stream().map(o -> {
            PortParameter portParameter = o.getAnnotation(PortParameter.class);
            String value = portParameter.value();
            //compileContext.log.warn(element, "PortMethodParameterProcessor process:%s", value);
            return new PortMethodParameter(o, value);
        }).collect(Collectors.toList());
    }
}
