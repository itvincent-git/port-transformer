package net.port.transformer.compiler;


import net.port.transformer.compiler.common.CompilerContext;

import java.util.List;

import javax.lang.model.element.Element;
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

    void process() {
        List<? extends Element> allMembers = Util.getAllMembers(compileContext.processingEnvironment, transformerElement);
        compileContext.log.debug("process: %s", allMembers.stream().toArray());
    }
}
