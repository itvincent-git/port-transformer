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
    private static final String STRING_PORT_DATA_NAME = "net.port.transformer.data.StringPortData";
    public ExecutableElement executableElement;
    public List<PortMethodParameter> portMethodParameterList;
    /**
     * processor的类型
     */
    public TypeElement processorTypeName;
    /**
     * PortData的DeclaredType类型
     */
    public DeclaredType processorPortDataDeclareType;
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
        TypeMirror typeParameterMirrorOfInterface = Util.getTypeParameterMirrorOfInterface(processorTypeName, 0, 1);
        this.processorPortDataDeclareType = Util.asDeclared(typeParameterMirrorOfInterface);
        //todo 判断是不是PortData类型
        this.isStringPortData =
                Util.toTypeElement(typeParameterMirrorOfInterface).getQualifiedName()
                        .contentEquals(STRING_PORT_DATA_NAME);
    }
}
