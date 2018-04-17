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

    public PortMethodParameter(VariableElement element, String parameterKey) {
        this.element = element;
        this.parameterKey = parameterKey;
        parameterSpec = ParameterSpec
                .builder(TypeName.get(element.asType()), element.getSimpleName().toString())
                .build();

    }
}
