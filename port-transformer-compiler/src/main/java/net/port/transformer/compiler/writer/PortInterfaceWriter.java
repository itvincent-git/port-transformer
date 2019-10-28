package net.port.transformer.compiler.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortInterfaceData;

import javax.lang.model.element.Modifier;

/**
 * 生成SampleReport_Impl
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceWriter extends PortClassWriter {
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
            method.addStatement("$T __p = new $T()", portMethod.processorPortDataType,
                    portMethod.processorPortDataType);
            //可变参数
            portMethod.portMethodParameterList.stream().forEach(portMethodParameter -> {

                if (!portMethodParameter.isStringParameterType() && portMethod.isStringPortData) {
                    method.addStatement("__p.putValue($S, $T.valueOf($N))", portMethodParameter.parameterKey,
                            ClassName.get(String.class), portMethodParameter.parameterSpec);
                } else {
                    method.addStatement("__p.putValue($S, $N)", portMethodParameter.parameterKey,
                            portMethodParameter.parameterSpec);
                }

            });
            //固定参数
            portMethod.portPairData.pairDataList.stream().forEach(pairData ->
                    method.addStatement("__p.putValue($L, $L)", pairData.key, pairData.value));
            method.addStatement("$T __processor = new $T()",
                    portMethod.processorTypeName, portMethod.processorTypeName);
            method.addStatement("__processor.doProcess(__p)");
            builder.addMethod(method.build());
        });
    }

}
