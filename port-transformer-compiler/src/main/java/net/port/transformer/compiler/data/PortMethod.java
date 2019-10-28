package net.port.transformer.compiler.data;

import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.util.Util;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/**
 * represent the @PortInterface method like > SampleReport.report()
 * Created by zhongyongsheng on 2018/4/16.
 */
public class PortMethod {
    public ExecutableElement executableElement;
    public List<PortMethodParameter> portMethodParameterList;
    public TypeElement processorTypeName;
    public TypeElement processorPortDataType;
    /**
     * processor是否StringPortData
     * 如果true，则表示需要将传入的各种参数类型会转换为String
     */
    public Boolean isStringPortData;
    public PortPairData portPairData;

    public PortMethod(CompilerContext compileContext,
                      ExecutableElement executableElement, List<PortMethodParameter> portMethodParameterList,
                      TypeMirror processorTypeMirror, PortPairData portPairData) {
        this.executableElement = executableElement;
        this.portMethodParameterList = portMethodParameterList;
        this.portPairData = portPairData;
        this.processorTypeName = Util.toTypeElement(processorTypeMirror);
        TypeMirror typeMirror = processorTypeName.getInterfaces().get(0);
        compileContext.log.debug("processorTypeName %s", typeMirror);
        DeclaredType declaredType = Util.asDeclared(typeMirror);
        compileContext.log.debug("typeElement %s", declaredType);
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        compileContext.log.debug("typeParameters size:%d", typeArguments.size());
        if (typeArguments != null && typeArguments.size() > 1) {
            TypeMirror mirror = typeArguments.get(1);
            processorPortDataType = Util.toTypeElement(mirror);
            compileContext.log.debug("typeParameters get(1) %s", processorPortDataType.getQualifiedName());
            this.isStringPortData =
                    this.processorPortDataType.getQualifiedName()
                            .contentEquals("net.port.transformer.data.StringPortData");
            compileContext.log.debug("isStringPortData %b", isStringPortData);
        }
    }
}
