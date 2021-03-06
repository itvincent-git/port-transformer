package net.port.transformer.compiler.data;

import com.squareup.javapoet.ClassName;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.TypeElement;

/**
 * 保存接口及其方法的Data
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortTransformerData {
    public ClassName implTypeName;
    public ClassName typeName;
    public List<PortInterfaceMethod> portInterfaceMethodList;

    public PortTransformerData(TypeElement element, List<PortInterfaceMethod> portInterfaceMethodList) {
        typeName = ClassName.get(element);
        String implClassName = typeName.simpleNames().stream().collect(Collectors.joining("_")) + "_Impl";
        implTypeName = ClassName.get(typeName.packageName(), implClassName);

        this.portInterfaceMethodList = portInterfaceMethodList;
    }
}
