package net.port.transformer.processor;

import com.squareup.javapoet.TypeName;

import net.port.transformer.annotation.PortParameter;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortMethodParameter;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * 方法参数的处理
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortMethodParameterProcessor {

    CompilerContext compileContext;
    ExecutableElement element;

    PortMethodParameterProcessor(CompilerContext context, ExecutableElement element) {
        compileContext = context;
        this.element = element;
    }

    List<PortMethodParameter> process() {

        List<? extends VariableElement> parameters = element.getParameters();
        return parameters.stream().map(o -> {
            PortParameter portParameter = o.getAnnotation(PortParameter.class);
            String value = portParameter.value();
            compileContext.log.debug("parameter simple name %s", TypeName.get(o.asType()).toString());
            return new PortMethodParameter(o, value);
        }).collect(Collectors.toList());
    }
}
