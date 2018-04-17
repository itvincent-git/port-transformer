package net.port.transformer.compiler.writer;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortInterfaceData;
import net.port.transformer.compiler.data.PortInterfaceMethod;
import net.port.transformer.compiler.data.PortTransformerData;
import net.port.transformer.util.StringUtil;
import net.port.transformer.util.TmpVar;
import net.port.transformer.util.Util;

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
        createConstructor(builder);
        return builder;
    }

    private void createConstructor(TypeSpec.Builder builder) {
//        builder.addMethod(MethodSpec.constructorBuilder().addParameter()
//                .addModifiers(Modifier.PUBLIC)
//                .addStatement("this.$N = $N", field, param)
//                .build());
    }

}
