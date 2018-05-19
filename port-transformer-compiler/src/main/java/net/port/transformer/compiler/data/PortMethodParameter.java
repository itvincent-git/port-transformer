package net.port.transformer.compiler.data;

import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.VariableElement;

/**
 * Created by zhongyongsheng on 2018/4/16.
 */
public class PortMethodParameter {
    public VariableElement element;
    public String parameterKey;
    public ParameterSpec parameterSpec;
    public TypeName parameterType;

    public PortMethodParameter(VariableElement element, String parameterKey) {
        this.element = element;
        this.parameterKey = parameterKey;
        this.parameterType = TypeName.get(element.asType());
        parameterSpec = ParameterSpec
                .builder(this.parameterType, element.getSimpleName().toString())
                .build();

    }

    public boolean isStringParameterType() {
        return this.parameterType.toString().equals("java.lang.String");
    }
}
