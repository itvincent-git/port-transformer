package net.port.transformer.processor;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.util.Util;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
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

        List<ExecutableElement> methods = Util.getAllMembers(compileContext.processingEnvironment, interfaceElement)
                .stream()
                .filter(o -> o.getKind().equals(ElementKind.METHOD))
                .map(o -> Util.asExecutable(o))
                .collect(Collectors.toList());

        PortInterfaceData portInterfaceData = new PortInterfaceData(interfaceElement, declaredType, methods);
        return portInterfaceData;
    }
}
