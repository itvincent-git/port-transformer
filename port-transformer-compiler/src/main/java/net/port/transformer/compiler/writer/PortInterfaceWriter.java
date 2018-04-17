package net.port.transformer.compiler.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.data.DefaultPortData;
import net.port.transformer.data.PortData;
import net.port.transformer.processor.PortTransformerProcessor;

import javax.lang.model.element.Modifier;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceWriter extends PortClassWriter{
    PortInterfaceData portInterfaceData;

    public PortInterfaceWriter(PortInterfaceData portInterfaceData) {
        super(portInterfaceData.implTypeName);
        this.portInterfaceData = portInterfaceData;
    }

    @Override
    protected TypeSpec.Builder createTypeSpecBuilder() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(portInterfaceData.implTypeName);
        builder.addModifiers(Modifier.PUBLIC)
                .addSuperinterface(portInterfaceData.typeName);
        createMethods(builder);
        return builder;
    }

    private void createMethods(TypeSpec.Builder builder) {
        portInterfaceData.methods.stream().forEach(portMethod -> {
            MethodSpec.Builder method = MethodSpec.overriding(portMethod.executableElement);
            method.addStatement("$T __p = new $T()", ClassName.get(PortData.class), ClassName.get(DefaultPortData.class));
            portMethod.portMethodParameterList.stream().forEach(portMethodParameter -> {
                method.addStatement("__p.putValue($S, $N)", portMethodParameter.parameterKey, portMethodParameter.parameterSpec);
            });
            method.addStatement("$T __processor = new $T()",
                    ClassName.get(PortTransformerProcessor.class), ClassName.get(portMethod.processorTypeMirror));
            method.addStatement("__processor.doProcess(__p)");
            builder.addMethod(method.build());
        });
    }

}
