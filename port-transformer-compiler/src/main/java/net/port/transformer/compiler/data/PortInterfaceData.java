package net.port.transformer.compiler.data;

import com.squareup.javapoet.ClassName;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortInterfaceData {
    public TypeElement element;
    public DeclaredType declaredType;
    public List<PortMethod> methods;
    public ClassName typeName;
    public ClassName implTypeName;

    public PortInterfaceData(TypeElement element, DeclaredType declaredType, List<PortMethod> methods) {
        this.element = element;
        this.declaredType = declaredType;
        this.methods = methods;
        this.typeName = ClassName.get(element);
        this.implTypeName = ClassName.get(this.typeName.packageName(), implClassName());
    }

    private String implClassName() {
        return this.typeName.simpleName() + "_Impl";
    }

}
