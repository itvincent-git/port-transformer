package net.port.transformer.util;

import com.google.auto.common.AnnotationMirrors;
import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class Util {

    /**
     * Element转化为TypeElement，包含类的名称，实现的接口，父类等
     * @param element
     * @return
     */
    public static TypeElement toTypeElement(Element element) {
        return MoreElements.asType(element);
    }

    /**
     * 转化为可执行方法的Element，包含方法的参数、返回值
     * @param element
     * @return
     */
    public static ExecutableElement asExecutable(Element element) {
        return MoreElements.asExecutable(element);
    }

    /**
     * TypeMirror转化为TypeElement，包含类的名称，实现的接口，父类等
     * @param typeMirror
     * @return
     */
    public static TypeElement toTypeElement(TypeMirror typeMirror) {
        return MoreTypes.asTypeElement(typeMirror);
    }

    /**
     * Element转化为DeclaredType，包含类型参数
     * @param element
     * @return
     */
    public static DeclaredType asDeclared(Element element) {
        return MoreTypes.asDeclared(element.asType());
    }

    /**
     * AnnotationMirror 包含注解的类型，注解元素的值
     * @param element
     * @param annotationClass
     * @return
     */
    public static AnnotationMirror getAnnotationMirror(Element element, Class<? extends Annotation> annotationClass) {
        return MoreElements.getAnnotationMirror(element, annotationClass).orNull();
    }

    /**
     * 根据元素名称返回注解的元素的值
     * @param annotationMirror
     * @param name
     * @return
     */
    public static AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String name) {
        return AnnotationMirrors.getAnnotationValue(annotationMirror, name);
    }

    /**
     * 返回所有成员变量及方法
     * @param processingEnvironment
     * @param element
     * @return
     */
    public static List<? extends Element> getAllMembers(ProcessingEnvironment processingEnvironment, TypeElement element) {
        return processingEnvironment.getElementUtils().getAllMembers(element);
    }

    public static TypeMirror annotationValueToType(AnnotationValue annotationValue) {
        return TO_TYPE.visit(annotationValue);
    }

    private static SimpleAnnotationValueVisitor6<TypeMirror, Void> TO_TYPE = new SimpleAnnotationValueVisitor6<TypeMirror, Void>(){
        @Override
        public TypeMirror visitType(TypeMirror typeMirror, Void aVoid) {
            return typeMirror;
        }

        @Override
        protected TypeMirror defaultAction(Object o, Void aVoid) {
            throw new TypeNotPresentException(o.toString(), null);
        }
    };

}
