package net.port.transformer.compiler.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortInterfaceMethod;
import net.port.transformer.compiler.data.PortTransformerData;
import net.port.transformer.util.StringUtil;
import net.port.transformer.util.TmpVar;
import net.port.transformer.util.Util;

import javax.lang.model.element.Modifier;

/**
 * 生成ReportHelper_Impl
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortTransformerWriter extends PortClassWriter {
    PortTransformerData portTransformerData;

    public PortTransformerWriter(PortTransformerData portTransformerData) {
        super(portTransformerData.implTypeName);
        this.portTransformerData = portTransformerData;
    }

    @Override
    protected TypeSpec.Builder createTypeSpecBuilder() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(portTransformerData.implTypeName);
        builder.addModifiers(Modifier.PUBLIC)
                .addAnnotation(ClassName.get("android.support.annotation", "Keep"))
                .superclass(portTransformerData.typeName);
        addInterfaceMethod(builder);
        return builder;
    }

    private void addInterfaceMethod(TypeSpec.Builder builder) {
        TmpVar tmpVar = new TmpVar();
        portTransformerData.portInterfaceMethodList.forEach(portInterfaceMethod -> {
            String name = StringUtil.decapitalize(
                    portInterfaceMethod.portInterfaceData.typeName.simpleName());
            String fieldName = tmpVar.getTmpVar("_" + name);
            FieldSpec field = FieldSpec.builder(portInterfaceMethod.portInterfaceData.typeName,
                    fieldName,
                    Modifier.PRIVATE,
                    Modifier.VOLATILE)
                    .build();
            builder.addField(field);
            builder.addMethod(createInterfaceGetter(field, portInterfaceMethod));
        });
    }

    private MethodSpec createInterfaceGetter(FieldSpec field,
                                             PortInterfaceMethod portInterfaceMethod) {
        MethodSpec.Builder methodBuilder =
                MethodSpec.overriding(Util.asExecutable(portInterfaceMethod.element));
        methodBuilder.beginControlFlow("if ($N != null)", field).addStatement("return $N", field);
        methodBuilder.nextControlFlow("else")
                .beginControlFlow("synchronized(this)")
                .beginControlFlow("if ($N == null)", field)
                .addStatement("$N = new $T()", field,
                        portInterfaceMethod.portInterfaceData.implTypeName)
                .endControlFlow()
                .addStatement("return $N", field)
                .endControlFlow()
                .endControlFlow();
        methodBuilder.addModifiers(Modifier.PUBLIC);
        return methodBuilder.build();
    }
}
