package net.port.transformer.processor;


import net.port.transformer.compiler.writer.PortInterfaceWriter;
import net.port.transformer.compiler.writer.PortTransformerWriter;
import net.port.transformer.util.Util;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.compiler.data.PortInterfaceMethod;
import net.port.transformer.compiler.data.PortTransformerData;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
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

    /**
     * 解析接口对象及其接口的所有属性
     * @PortInterface
     * public interface SampleReport
     *
     * @return
     */
    PortTransformerData process() {
        List<? extends Element> allMembers = Util.getAllMembers(compileContext.processingEnvironment, transformerElement);
        List<PortInterfaceMethod> portInterfaceMethodList = allMembers.stream()
                .filter(
                        (Predicate<Element>) element ->
                                element.getModifiers().contains(Modifier.ABSTRACT) && element.getKind().equals(ElementKind.METHOD))
                .map((Function<Element, PortInterfaceMethod>) element -> {
                    /**
                     * 解析方法的属性
                     * void report(@PortParameter("function_id")String funcId, @PortParameter("act_uid")String uid)
                     */
                    ExecutableElement methodElement = Util.asExecutable(element);
                    TypeElement interfaceType = Util.toTypeElement(methodElement.getReturnType());
                    PortInterfaceData data = new PortInterfaceProcessor(compileContext, interfaceType).process();
                    PortInterfaceMethod method = new PortInterfaceMethod(methodElement, methodElement.getSimpleName().toString(), data);
                    return method;
                }).collect(Collectors.toList());
        PortTransformerData portTransformerData = new PortTransformerData(transformerElement, portInterfaceMethodList);

        portInterfaceMethodList.forEach(portInterfaceMethod -> {
            try {
                new PortInterfaceWriter(portInterfaceMethod.portInterfaceData).write(compileContext.processingEnvironment);
            } catch (IOException e) {
                compileContext.log.error("PortInterfaceWriter error", e.getMessage());
            }

        });
        return portTransformerData;
    }
}
