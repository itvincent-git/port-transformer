package net.port.transformer.processor;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.compiler.data.PortMethod;
import net.port.transformer.util.Util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceProcessor {

    CompilerContext compileContext;
    TypeElement interfaceElement;

    PortInterfaceProcessor(CompilerContext context, TypeElement element) {
        compileContext = context;
        interfaceElement = element;
    }

    PortInterfaceData process() {
        if (interfaceElement.getAnnotation(PortInterface.class) == null) {
            compileContext.log.error(interfaceElement, interfaceElement.getSimpleName() + "must has @PortInterface");
        }
        if (!interfaceElement.getKind().equals(ElementKind.INTERFACE)) {
            compileContext.log.error(interfaceElement, interfaceElement.getSimpleName() + "must be interface");
        }

        DeclaredType declaredType = Util.asDeclared(interfaceElement);

        Stream<ExecutableElement> executableElementStream = Util.getAllMembers(compileContext.processingEnvironment, interfaceElement)
                .stream()
                .filter(o -> o.getModifiers().contains(Modifier.ABSTRACT)
                        && o.getKind().equals(ElementKind.METHOD)
                        && o.getAnnotation(PortProcessor.class) != null)
                .map(o -> Util.asExecutable(o));

        List<PortMethod> portMethodList = executableElementStream
                .map(executableElement -> new PortMethodProcessor(compileContext, executableElement).process())
                .collect(Collectors.toList());

        PortInterfaceData portInterfaceData = new PortInterfaceData(interfaceElement, declaredType, portMethodList);
        return portInterfaceData;
    }
}
