package net.port.transformer.compiler.writer;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortInterfaceData;

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
            method.addStatement("net.port.transformer.PortData p = new net.port.transformer.DefaultPortData()");
//            portMethod.portMethodParameterList.stream().forEach(portMethodParameter -> {
//                ParameterSpec.builder(portMethodParameter.element).build()
//            });
            builder.addMethod(method.build());
        });
    }

}
