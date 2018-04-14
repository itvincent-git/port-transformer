package net.port.transformer.compiler;


import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.compiler.data.PortTransformerData;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortTransformerAnnotationProcessor {
    CompilerContext compileContext;
    TypeElement transformerElement;

    PortTransformerAnnotationProcessor(CompilerContext context, TypeElement element) {
        compileContext = context;
        transformerElement = element;
    }

    PortTransformerData process() {
        List<? extends Element> allMembers = Util.getAllMembers(compileContext.processingEnvironment, transformerElement);
        compileContext.log.warn("process:");
        allMembers.stream()
                .filter(
                        (Predicate<Element>) element ->
                                element.getModifiers().contains(Modifier.ABSTRACT) && element.getKind().equals(ElementKind.METHOD))
                .map(new Function<Element, PortInterfaceData>() {
                    @Override
                    public PortInterfaceData apply(Element element) {
                        TypeElement interfaceType = Util.toTypeElement(element);
                        PortInterfaceData data = new PortInterfaceProcessor(compileContext, interfaceType).process();
                        return data;
                    }
                });
        PortTransformerData portTransformerData = new PortTransformerData(transformerElement);
        return portTransformerData;
    }
}
