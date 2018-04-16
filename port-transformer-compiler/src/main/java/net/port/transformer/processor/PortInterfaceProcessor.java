package net.port.transformer.processor;

import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortInterfaceData;

import javax.lang.model.element.TypeElement;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceProcessor {

    CompilerContext compileContext;
    TypeElement transformerElement;

    PortInterfaceProcessor(CompilerContext context, TypeElement element) {
        compileContext = context;
        transformerElement = element;
    }

    PortInterfaceData process() {
        PortInterfaceData portInterfaceData = new PortInterfaceData();
        return portInterfaceData;
    }
}
