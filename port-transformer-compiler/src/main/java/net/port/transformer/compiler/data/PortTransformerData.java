package net.port.transformer.compiler.data;

import com.squareup.javapoet.ClassName;

import java.util.stream.Collectors;

import javax.lang.model.element.TypeElement;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortTransformerData {
    public ClassName implTypeName;
    public ClassName typeName;

    public PortTransformerData(TypeElement element) {
        typeName = ClassName.get(element);
        String implClassName = typeName.simpleNames().stream().collect(Collectors.joining("_")) + "_Impl";
        implTypeName = ClassName.get(typeName.packageName(), implClassName);
    }
}
