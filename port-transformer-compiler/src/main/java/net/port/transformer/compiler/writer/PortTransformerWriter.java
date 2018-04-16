package net.port.transformer.compiler.writer;

import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.data.PortTransformerData;

import javax.lang.model.element.Modifier;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortTransformerWriter extends PortClassWriter{
    PortTransformerData portTransformerData;

    public PortTransformerWriter(PortTransformerData portTransformerData) {
        super(portTransformerData.implTypeName);
        this.portTransformerData = portTransformerData;
    }

    @Override
    protected TypeSpec.Builder createTypeSpecBuilder() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(portTransformerData.implTypeName);
        builder.addModifiers(Modifier.PUBLIC)
                .superclass(portTransformerData.typeName);
        addInterfaceMethod(builder);
        return builder;
    }

    private void addInterfaceMethod(TypeSpec.Builder builder) {

    }
}
